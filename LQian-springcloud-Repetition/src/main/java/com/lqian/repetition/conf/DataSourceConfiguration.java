package com.lqian.repetition.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author zl
 * 配置数据源管理器
 */
@Configuration
@EnableTransactionManagement
@Order(1)
public class DataSourceConfiguration {

    /**************master库控制********************/
    @Value("${spring.datasource.druid.master.url}")
    private String  masterurl;
    @Value("${spring.datasource.druid.master.username}")
    private String masterusername;
    @Value("${spring.datasource.druid.master.password}")
    private String masterpassword;
    @Value("${spring.datasource.druid.master.validation-query}")
    private String mastervalidationquery;
    @Value("${spring.datasource.druid.master.initial-size}")
    private int masterinitialsize;
    @Value("${spring.datasource.druid.master.max-active}")
    private int mastermaxactive;
    @Value("${spring.datasource.druid.master.min-idle}")
    private int masterminidle;
    @Value("${spring.datasource.druid.master.max-wait}")
    private Long mastermaxwait;
    @Value("${spring.datasource.druid.master.remove-abandoned}")
    private Boolean masterremoveabandoned;
    @Value("${spring.datasource.druid.master.remove-abandoned-timeout-millis}")
    private int masterremoveabandonedtimeoutmillis;
    @Value("${spring.datasource.druid.master.time-between-eviction-runs-millis}")
    private Long mastertimebetweenevictionrunsmillis;
    @Value("${spring.datasource.druid.master.min-evictable-idle-time-millis}")
    private Long masterminevictableidletimemillis;
    @Value("${spring.datasource.druid.master.filters}")
    private String masterfilters;
    @Value("${spring.datasource.druid.master.stat-view-servlet.enabled}")
    private boolean masterstatviewservlet;
    @Value("${spring.datasource.druid.master.use-global-data-source-stat}")
    private Boolean masteruseglobaldatasourcestat;



    @Bean
    public DataSource dataSource()  throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(masterurl);
        dataSource.setUsername(masterusername);
        dataSource.setPassword(masterpassword);
        dataSource.setValidationQuery(mastervalidationquery);
        dataSource.setInitialSize(masterinitialsize);
        dataSource.setMaxActive(mastermaxactive);
        dataSource.setMinIdle(masterminidle);
        dataSource.setMaxWait(mastermaxwait);
        dataSource.setRemoveAbandoned(masterremoveabandoned);
        dataSource.setRemoveAbandonedTimeout(masterremoveabandonedtimeoutmillis);
        dataSource.setTimeBetweenEvictionRunsMillis(mastertimebetweenevictionrunsmillis);
        dataSource.setMinEvictableIdleTimeMillis(masterminevictableidletimemillis);
        dataSource.setFilters(masterfilters);
        dataSource.setResetStatEnable(masterstatviewservlet);
        dataSource.setUseGlobalDataSourceStat(masteruseglobaldatasourcestat);
        return dataSource;
    }

}
