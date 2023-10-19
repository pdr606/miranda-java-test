package com.example.javatest.config;

import com.example.javatest.mapper.CarMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {

    @Bean
    CarMapper carMapper(){
        return new CarMapper();
    }
}
