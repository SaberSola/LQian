package com.zl.lqian.config;


import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.zl.lqian.hystrix.MdcHystrixConcurrencyStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class HystrixConfig {

    /**
     * 采用注解的方式
     * 声明切面拦截注解
     */
    @Bean
    public HystrixCommandAspect hystrixCommandAspect(){

        return new HystrixCommandAspect();
    }


    @PostConstruct
    public void init(){

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MdcHystrixConcurrencyStrategy());
    }


}
