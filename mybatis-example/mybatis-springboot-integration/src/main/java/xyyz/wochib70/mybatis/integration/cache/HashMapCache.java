package xyyz.wochib70.mybatis.integration.cache;

import lombok.Setter;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class HashMapCache implements Cache, ApplicationContextAware {

    @Setter
    private String id;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HashMapCache.class);

    private final Map<Object, Object> map = new HashMap<>();

    private ApplicationContext applicationContext;

    public HashMapCache(String id) {
        this.id = id;
    }

    public HashMapCache() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        LOGGER.info("放入缓存，Key:{}; Value:{}", key, value);
        map.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        LOGGER.info("获取缓存，Key:{}", key);
        LOGGER.info("获取到了缓存：{}", map.get(key));
        return map.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        LOGGER.info("删除缓存，Key:{}", key);
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int getSize() {
        return map.size();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("设置ApplicationContext, {}", this);
        this.applicationContext = applicationContext;
    }

}
