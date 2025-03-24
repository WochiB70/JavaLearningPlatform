package xyyz.wochib70.mybatis.integration;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyyz.wochib70.mybatis.integration.entity.ExampleEntity;
import xyyz.wochib70.mybatis.integration.mapper.ExampleMapper;

import java.util.Random;

@SpringBootApplication
@Slf4j
public class MyBatisSpringBootApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MyBatisSpringBootApplication.class, args);
    }

    @Resource
    private ExampleMapper exampleMapper;


    @Override
    public void run(String... args) throws Exception {
        long id = new Random().nextLong(111L, 444568888L);
        ExampleEntity entity = new ExampleEntity(id, "test", "这是一个测试");
        exampleMapper.insert(entity);
        ExampleEntity example = exampleMapper.findById(id);
        log.info("查询到的数据：{}", example);
    }
}
