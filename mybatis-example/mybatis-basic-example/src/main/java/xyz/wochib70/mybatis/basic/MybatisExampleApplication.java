package xyz.wochib70.mybatis.basic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wochib70.mybatis.basic.dao.ExampleDao;
import xyz.wochib70.mybatis.basic.entity.ExampleEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MybatisExampleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisExampleApplication.class);


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
            ExampleEntity example = exampleDao.findByIdAndTableName(entity.getId(), "example");
            LOGGER.info(example.toString());
        }
    }
}
