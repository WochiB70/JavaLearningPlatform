package xyz.wochib70.mybatis.advanced.cache.dao;


import org.apache.ibatis.annotations.Mapper;
import xyz.wochib70.mybatis.advanced.cache.entity.CacheEntity;

import java.util.List;

@Mapper
public interface AdvancedCacheDao {


    void insert(CacheEntity entity);


    List<CacheEntity> selectListByLikeName(String name);
}
