package com.example.activitidemo;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = "com.example.activitidemo.mapper")
public class ActivitiDemoApplication {
//    @Configuration
//    @ComponentScan
//    @EnableAutoConfiguration
//    public static class  SpringBootConfiguration {
//
//    }
    public static void main(String[] args) {
        SpringApplication.run(ActivitiDemoApplication.class, args);
    }

}
