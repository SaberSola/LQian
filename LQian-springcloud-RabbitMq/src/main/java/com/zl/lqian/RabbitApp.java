package com.zl.lqian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient
@Configuration
public class RabbitApp {

    public static void main(String[] args){
        SpringApplication.run(RabbitApp.class,args);
    }
}
