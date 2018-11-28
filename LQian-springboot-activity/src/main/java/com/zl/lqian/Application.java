package com.zl.lqian;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan("com.zl.lqian.mapper")
@EnableEurekaClient
@EnableAutoConfiguration(exclude =   org.activiti.spring.boot.SecurityAutoConfiguration.class)
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
