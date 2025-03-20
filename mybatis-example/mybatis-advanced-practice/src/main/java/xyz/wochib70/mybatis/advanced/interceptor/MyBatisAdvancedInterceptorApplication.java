package xyz.wochib70.mybatis.advanced.interceptor;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wochib70.mybatis.advanced.interceptor.dao.AdvancedInterceptorDao;
import xyz.wochib70.mybatis.advanced.interceptor.entity.AdvancedInterceptorEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MyBatisAdvancedInterceptorApplication {

    public static final String SQL_SESSION_FACTORY_XML = "mybatis-configuration-interceptor.xml";

    public static final Logger LOGGER = LoggerFactory.getLogger(MyBatisAdvancedInterceptorApplication.class);


    public static void main(String[] args) throws IOException {
        InputStream resource = Resources.getResourceAsStream(SQL_SESSION_FACTORY_XML);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
        try (SqlSession sqlSession = factory.openSession(true)) {
            AdvancedInterceptorDao mapper = sqlSession.getMapper(AdvancedInterceptorDao.class);
            Random idGenerator = new Random();
            long id1 = idGenerator.nextLong(1111111111L, 9999999999L);
            long id2 = idGenerator.nextLong(1111111111L, 9999999999L);
            AdvancedInterceptorEntity entity1 = AdvancedInterceptorEntity.builder()
                    .id(id1)
                    .name("测试【占位符】用例1")
                    .description("测试用例1")
                    .build();
            AdvancedInterceptorEntity entity2 = AdvancedInterceptorEntity.builder()
                    .id(id2)
                    .name("测试用例2")
                    .description("测试用例2")
                    .build();
            mapper.insert(entity1);
            mapper.insert(entity2);
            AdvancedInterceptorEntity byId = mapper.findById(id2);
            LOGGER.info("查询的时Id为{}， 结果的id为：{}，结果为：{}", id2, byId.getId(), byId);
        }

    }
}
