package xyyz.wochib70.mybatis.integration;


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

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
}
