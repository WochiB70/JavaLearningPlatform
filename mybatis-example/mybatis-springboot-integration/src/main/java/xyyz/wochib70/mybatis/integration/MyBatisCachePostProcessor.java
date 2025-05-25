package xyyz.wochib70.mybatis.integration;


import jdk.jfr.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import xyyz.wochib70.mybatis.integration.cache.HashMapCache;

import java.util.*;

@Slf4j
@Component
public class MyBatisCachePostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private final Set<String> identifiers = new HashSet<>();

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MapperFactoryBean<?> mapperFactoryBean) {
            log.info("扫描到 MapperFactoryBean: {}, 开始处理其cache", beanName);
            Configuration configuration = mapperFactoryBean.getSqlSessionFactory().getConfiguration();
            Collection<MappedStatement> collection = configuration.getMappedStatements();
            for (MappedStatement statement : collection) {
                if (identifiers.contains(statement.getId())) {
                    log.info("已经处理过的 MapperFactoryBean: {}, 跳过", statement.getId());
                    continue;
                }
                MetaObject metaObject = SystemMetaObject.forObject(statement);
                LoggingCache cache = (LoggingCache) metaObject.getValue("cache");
                if (Objects.nonNull(cache)) {
                    log.info("MappedStatement {}, 配置了Cache， 需要被处理", statement.getId());
                    HashMapCache beanCache = applicationContext.getBean(HashMapCache.class);
                    beanCache.setId(cache.getId());
                    LoggingCache loggingCache = new LoggingCache(beanCache);
                    metaObject.setValue("cache", loggingCache);
                    log.info("old cache: {}, new cache: {}", cache, beanCache);
                    log.info("MappedStatement {}, 配置了Cache， 处理完成", statement.getId());
                }
                identifiers.add(statement.getId());
            }
        }

        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public enum ResourceType {
        CACHE,
        MAPPER,
        SQL_SESSION_FACTORY,
        SQL_SESSION_TEMPLATE,
        SQL_SESSION_TEMPLATE_FACTORY,
        TRANSACTION_MAN
    }

    public interface Resource<T extends ResourceType> {
        default Integer getResourceCount() {
            throw new UnsupportedOperationException();
        }

        default T getResourceType() {
            throw new UnsupportedOperationException();
        }
    }

    public interface UsedResourceEvent<T extends Collection<? extends Resource>> {

        default Collection<? extends Resource> getUsedResources() {
            throw new UnsupportedOperationException();
        }
    }

    public class UsedResourceEventImpl<T extends Collection<? extends Resource>> implements UsedResourceEvent<T> {

        private final T usedResources;

        public UsedResourceEventImpl(T usedResources) {
            this.usedResources = usedResources;
        }

        @Override
        public Collection<? extends Resource> getUsedResources() {
            for (Resource resource : usedResources) {
                ResourceType resourceType = resource.getResourceType();
                Integer resourceCount = resource.getResourceCount();
            }
            return usedResources;
        }
    }

}
