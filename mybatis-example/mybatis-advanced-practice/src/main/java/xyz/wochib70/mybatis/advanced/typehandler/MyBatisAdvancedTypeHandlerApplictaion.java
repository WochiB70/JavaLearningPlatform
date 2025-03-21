package xyz.wochib70.mybatis.advanced.typehandler;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wochib70.mybatis.advanced.typehandler.dao.TypeHandlerDao;
import xyz.wochib70.mybatis.advanced.typehandler.entity.JsonField;
import xyz.wochib70.mybatis.advanced.typehandler.entity.TypeHandlerEntity;

import java.io.InputStream;
import java.util.Random;

public class MyBatisAdvancedTypeHandlerApplictaion {

    public static final String SQL_SESSION_FACTORY_XML = "mybatis-configuration-typehandler.xml";

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisAdvancedTypeHandlerApplictaion.class);

    public static void main(String[] args) throws Exception {

        InputStream resource = Resources.getResourceAsStream(SQL_SESSION_FACTORY_XML);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);

        try (SqlSession sqlSession = factory.openSession(true)) {
            TypeHandlerDao mapper = sqlSession.getMapper(TypeHandlerDao.class);
            Random idGenerator = new Random();
            long id = idGenerator.nextLong(1111L, 551531111333L);
            TypeHandlerEntity entity = TypeHandlerEntity.builder()
                    .id(id)
                    .name("tenjsa")
                    .jsonField(JsonField.builder()
                            .name("key")
                            .code(1233)
                            .build())
                    .build();

            mapper.insert(entity);
            TypeHandlerEntity selected = mapper.selectById(id);
            LOGGER.info("selected data {}", selected);
        }
    }
}
