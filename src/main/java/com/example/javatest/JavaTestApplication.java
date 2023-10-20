package com.example.javatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableCaching
@SpringBootApplication
public class JavaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaTestApplication.class, args);
    }

}
