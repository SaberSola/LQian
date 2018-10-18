package com.zl.lqian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HttpClientPoolApplication {

    public static void main(String[] args){

        SpringApplication.run(HttpClientPoolApplication.class);
    }
}
