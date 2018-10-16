package com.zl.lqian;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.zl.lqian.mapper")
@MapperScan("com.zl.lqian.mapper")
public class AppApplication {

    public static void main(String[] args){
        SpringApplication.run(AppApplication.class, args);
    }
}
