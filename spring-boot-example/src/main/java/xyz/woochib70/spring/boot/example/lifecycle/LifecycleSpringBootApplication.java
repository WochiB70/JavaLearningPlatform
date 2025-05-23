package xyz.woochib70.spring.boot.example.lifecycle;


import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

@SpringBootApplication
public class LifecycleSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifecycleSpringBootApplication.class, args);
    }



}
