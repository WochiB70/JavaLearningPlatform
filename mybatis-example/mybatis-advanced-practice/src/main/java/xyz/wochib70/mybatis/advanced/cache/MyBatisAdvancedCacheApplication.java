package xyz.wochib70.mybatis.advanced.cache;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wochib70.mybatis.advanced.cache.dao.AdvancedCacheDao;
import xyz.wochib70.mybatis.advanced.cache.entity.CacheEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class MyBatisAdvancedCacheApplication {


    public static final String SQL_SESSION_FACTORY_XML = "mybatis-configuration-cache.xml";

    public static final Logger LOGGER = LoggerFactory.getLogger(MyBatisAdvancedCacheApplication.class);

    public static void main(String[] args) throws IOException {
        InputStream resource = Resources.getResourceAsStream(SQL_SESSION_FACTORY_XML);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
        try (SqlSession sqlSession = factory.openSession(true)) {
            AdvancedCacheDao mapper = sqlSession.getMapper(AdvancedCacheDao.class);
            Random idGenerator = new Random();
            for (int i = 0; i < 10; i++) {
                mapper.insert(CacheEntity
                        .builder()
                        .id(idGenerator.nextLong())
                        .name("测试经CDC的" + i)
                        .description("dsabjkdkasbjdasbdkabdjksa")
                        .build()
                );
            }
            LOGGER.info("开始第一次查询");
            List<CacheEntity> list = mapper.selectListByLikeName("CDC");
            LOGGER.info("查询的数量为：{}", list.size());

        }


        try (SqlSession sqlSession = factory.openSession(true)) {
            AdvancedCacheDao mapper = sqlSession.getMapper(AdvancedCacheDao.class);
            LOGGER.info("开始第二次查询");
            List<CacheEntity> list = mapper.selectListByLikeName("CDC");
            LOGGER.info("查询的数量为：{}", list.size());
        }

        try (SqlSession sqlSession = factory.openSession(true)) {

            LOGGER.info("开始第三次查询");
            List<CacheEntity> list = sqlSession.selectList("xyz.wochib70.mybatis.advanced.cache.dao.AdvancedCacheDao.selectListByLikeName", "CDC");
            LOGGER.info("查询的数量为：{}", list.size());
        }

    }
}
