package xyyz.wochib70.mybatis.integration.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyyz.wochib70.mybatis.integration.entity.ExampleEntity;

@Mapper
public interface ExampleMapper {

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
