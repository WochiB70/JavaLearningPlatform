package xyz.wochib70.mybatis.spring.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.wochib70.mybatis.spring.entity.ExampleEntity;

@Mapper
public interface ExampleDao {

//    @Insert("""
//            INSERT INTO example VALUES (#{id}, #{name}, #{description});
//
//            """)
    Long insert(ExampleEntity exampleEntity);


//    @Select("""
//            SELECT *
//            FROM example
//            WHERE id = #{id};
//            """)
    ExampleEntity findById(Long id);
}
