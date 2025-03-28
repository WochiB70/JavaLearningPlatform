package xyyz.wochib70.mybatis.integration.mapper;

import org.apache.ibatis.annotations.*;
import xyyz.wochib70.mybatis.integration.cache.HashMapCache;
import xyyz.wochib70.mybatis.integration.entity.ExampleEntity;

@Mapper
@CacheNamespace(implementation = HashMapCache.class)
public interface ExampleMapper {

    @Options(useCache = false)
    @Insert("""
            INSERT INTO example VALUES (#{id}, #{name}, #{description});
            """)
    Long insert(ExampleEntity exampleEntity);


    @Select("""
            SELECT *
            FROM example
            WHERE id = #{id}
            """)
    ExampleEntity findById(Long id);
}
