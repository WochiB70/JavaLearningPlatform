package xyz.wochib70.mybatis.basic.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.wochib70.mybatis.basic.entity.ExampleEntity;

@Mapper
public interface ExampleDao {

    Long insert(ExampleEntity exampleEntity);

    ExampleEntity findByIdAndTableName(@Param("id") Long id, @Param("tableName") String tableName);

    ExampleEntity findById(Long id);
}
