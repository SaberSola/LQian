package com.zl.lqian;


import com.zl.lqian.annotation.EnableCanalClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCanalClient
@MapperScan(basePackages = "com.zl.lqian.mapper")
public class CanalClientApplication {


    public static void main(String[] args){
        SpringApplication.run(CanalClientApplication.class,args);
    }
}
