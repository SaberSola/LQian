package com.zl.lqian.configuration;


import com.zl.lqian.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.zl.lqian.*"})
public class AutoConfiguration {

    private final ConfigProperties configProperties;

    @Autowired
    public AutoConfiguration(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }


    /**
     *  这里可以以后写一些初始化的资源信息
     *  note 此工程只是为了例子 不建议我这种写法
     */
    @Bean
    public void tccTransactionBootstrap() {

        System.out.print("初始化资源");
    }

}
