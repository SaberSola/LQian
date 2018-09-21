package com.zl.lqian;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//词注解开启异步
public class TicketApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(TicketApplicaton.class, args);
    }
}
