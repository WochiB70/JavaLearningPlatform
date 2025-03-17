package xyz.wochib70.example.dao;


import org.apache.ibatis.annotations.Mapper;
import xyz.wochib70.example.entity.ExampleEntity;

@Mapper
public interface ExampleDao {

    Long insert(ExampleEntity exampleEntity);

    ExampleEntity findById(Long id);
}
