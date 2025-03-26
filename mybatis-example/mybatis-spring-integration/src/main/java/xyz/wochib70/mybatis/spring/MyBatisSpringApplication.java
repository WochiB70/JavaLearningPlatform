package xyz.wochib70.mybatis.spring;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.wochib70.mybatis.spring.dao.ExampleDao;
import xyz.wochib70.mybatis.spring.entity.ExampleEntity;

import java.util.Random;
import java.util.stream.Stream;

public class MyBatisSpringApplication {


    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis-configuration.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationConfiguration.class);
        ExampleDao mapper = context.getBean(ExampleDao.class);
        long id = new Random().nextLong(1231L, 8456456456465L);
        ExampleEntity entity = ExampleEntity.builder()
                .id(id)
                .name("test")
                .description("spring mybatis test")
                .build();
        mapper.insert(entity);
        ExampleEntity example = mapper.findById(id);
        System.out.println(example);
    }
}
