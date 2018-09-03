package com.zl.lqian.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages ="com.zl.lqian.mapper" ,sqlSessionTemplateRef = "serviceSqlSessionTemplate")  //TODO 扫描mapper文件
public class ServcieDataSourceConfig {

    @Value("${com.zl.lqian.auth.datasource.username}")
    private String userName;

    @Value("${com.zl.lqian.auth.datasource.password}")
    private String password;

    @Value("${com.zl.lqian.auth.datasource.url}")
    private String url;

    @Value("${com.zl.lqian.auth.datasource.driver-class-name}")
    private String driverClass;


    @Bean(name = "serviceDS") @Qualifier("serviceDS")
    public DataSource serviceSource(){

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }

    @Bean(name = "serviceSqlSessionFactory")
    public SqlSessionFactory serviceSqlSessionFactory(@Qualifier("serviceDS") DataSource dataSource) throws Exception{

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "serviceTransactionManager")
    public DataSourceTransactionManager serviceTransactionManager(@Qualifier("serviceDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "serviceSqlSessionTemplate")
    public SqlSessionTemplate serviceSqlSessionTemplate(@Qualifier("serviceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
