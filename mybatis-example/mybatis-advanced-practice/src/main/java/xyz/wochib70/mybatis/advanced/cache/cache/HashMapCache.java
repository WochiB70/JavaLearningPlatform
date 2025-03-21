package xyz.wochib70.mybatis.advanced.cache.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HashMapCache implements Cache {

    private final String id;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HashMapCache.class);

    private final Map<Object, Object> map = new HashMap<>();

    public HashMapCache(String id) {
        this.id = id;
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
}
