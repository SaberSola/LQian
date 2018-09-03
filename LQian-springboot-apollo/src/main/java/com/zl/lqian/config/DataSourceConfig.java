package com.zl.lqian.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${com.zl.lqian.apollo.datasource.url}")
    private String apollo_url;

    @Value("${com.zl.lqian.apollo.datasource.username}")
    private String apollo_userName;

    @Value("${com.zl.lqian.apollo.datasource.password}")
    private String apollo_password;

    @Value("${com.zl.lqian.apollo.datasource.driver-class-name}")
    private String apollo_driver_class;


    @Bean(name = "apolloDS") @Qualifier("apolloDS")
    @Primary
    public DataSource authDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(apollo_driver_class);
        dataSource.setUrl(apollo_url);
        dataSource.setUsername(apollo_userName);
        dataSource.setPassword(apollo_password);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
}
