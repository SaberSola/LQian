package com.zl.lqian;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.zl.lqian.*"},
        exclude = {DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@EnableFeignClients
@EnableResourceServer
@EnableOAuth2Client
@EnableTransactionManagement
@MapperScan("com.zl.lqian.mapper")
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
