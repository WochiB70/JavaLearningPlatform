package xyz.wochib70.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xyz.wochib70.example.dao.ExampleDao;
import xyz.wochib70.example.entity.ExampleEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MybatisExampleApplication {


    public static void main(String[] args) throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-configuration.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        ExampleEntity entity = ExampleEntity.builder()
                .id(new Random().nextLong())
                .name("test")
                .description("这是一个测试")
                .build();

        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ExampleDao exampleDao = sqlSession.getMapper(ExampleDao.class);
            exampleDao.insert(entity);
            sqlSession.commit();
            ExampleEntity example = exampleDao.findById(entity.getId());
            System.out.println(example);
        }
    }
}
