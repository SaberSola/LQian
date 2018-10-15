package com.zl.lqian.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zl.lqian.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    final DataSourceProperties dataSourceProperties;

    @Autowired
    public DataSourceConfig(DataSourceProperties dataSourceProperties){
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        LogUtil.info(LOGGER,()->"init Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单
        servletRegistrationBean.addInitParameter("allow", "*");
        // IP黑名单(共同存在时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    @Primary
    public DataSource dataSource() {

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dataSourceProperties.getUrl());
        datasource.setUsername(dataSourceProperties.getUsername());
        datasource.setPassword(dataSourceProperties.getPassword());
        datasource.setDriverClassName(dataSourceProperties.getDriverClassName());

        //configuration
        datasource.setInitialSize(dataSourceProperties.getInitialSize());
        datasource.setMinIdle(dataSourceProperties.getMinIdle());
        datasource.setMaxActive(dataSourceProperties.getMaxActive());
        datasource.setMaxWait(dataSourceProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataSourceProperties.getValidationQuery());
        datasource.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        datasource.setTestOnReturn(dataSourceProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(dataSourceProperties.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(dataSourceProperties.getFilters());
        } catch (SQLException e) {
            LogUtil.error(LOGGER,()->"druid configuration initialization filter: ");
        }
        datasource.setConnectionProperties(dataSourceProperties.getConnectionProperties());
        return  datasource;
    }



}
