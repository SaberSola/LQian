package com.zl.lqian.readwrite.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.zl.lqian.readwrite.db.DbContextHolder;
import com.zl.lqian.readwrite.db.MasterSlaveRoutingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zl
 * 配置数据源管理器
 */
@Configuration
@EnableTransactionManagement
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


    /******************************从库配置***************************************/


    /**************master库控制********************/
    @Value("${spring.datasource.druid.slave.url}")
    private String  slaveurl;
    @Value("${spring.datasource.druid.slave.username}")
    private String slaveusername;
    @Value("${spring.datasource.druid.slave.password}")
    private String slavepassword;
    @Value("${spring.datasource.druid.slave.validation-query}")
    private String slavevalidationquery;
    @Value("${spring.datasource.druid.slave.initial-size}")
    private int slaveinitialsize;
    @Value("${spring.datasource.druid.slave.max-active}")
    private int slavemaxactive;
    @Value("${spring.datasource.druid.slave.min-idle}")
    private int slaveminidle;
    @Value("${spring.datasource.druid.slave.max-wait}")
    private Long slavemaxwait;
    @Value("${spring.datasource.druid.slave.remove-abandoned}")
    private Boolean slaveremoveabandoned;
    @Value("${spring.datasource.druid.slave.remove-abandoned-timeout-millis}")
    private int slaveremoveabandonedtimeoutmillis;
    @Value("${spring.datasource.druid.slave.time-between-eviction-runs-millis}")
    private Long slavetimebetweenevictionrunsmillis;
    @Value("${spring.datasource.druid.slave.min-evictable-idle-time-millis}")
    private Long slaveminevictableidletimemillis;
    @Value("${spring.datasource.druid.slave.filters}")
    private String slavefilters;
    @Value("${spring.datasource.druid.slave.stat-view-servlet.enabled}")
    private boolean slavestatviewservlet;
    @Value("${spring.datasource.druid.slave.use-global-data-source-stat}")
    private Boolean slaveuseglobaldatasourcestat;






    @Bean(name = "masterDataSource")
    public DataSource masterDataSource()  throws SQLException {
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

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource1() throws SQLException{
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(slaveurl);
        dataSource.setUsername(slaveusername);
        dataSource.setPassword(slavepassword);
        dataSource.setValidationQuery(slavevalidationquery);
        dataSource.setInitialSize(slaveinitialsize);
        dataSource.setMaxActive(slavemaxactive);
        dataSource.setMinIdle(slaveminidle);
        dataSource.setMaxWait(slavemaxwait);
        dataSource.setRemoveAbandoned(slaveremoveabandoned);
        dataSource.setRemoveAbandonedTimeout(slaveremoveabandonedtimeoutmillis);
        dataSource.setTimeBetweenEvictionRunsMillis(slavetimebetweenevictionrunsmillis);
        dataSource.setMinEvictableIdleTimeMillis(slaveminevictableidletimemillis);
        dataSource.setFilters(slavefilters);
        dataSource.setResetStatEnable(slavestatviewservlet);
        dataSource.setUseGlobalDataSourceStat(slaveuseglobaldatasourcestat);
        return dataSource;
    }

    /**
     *
     *  注册数据源
     * @return
     * @throws SQLException
     */
    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSource() throws SQLException {
        MasterSlaveRoutingDataSource proxy = new MasterSlaveRoutingDataSource();
        Map<Object,Object> targetDataSource = new HashMap();
        targetDataSource.put(DbContextHolder.DbType.DBMaster, masterDataSource());
        targetDataSource.put(DbContextHolder.DbType.DBSlave, slaveDataSource1());
        //默认使用主库
        proxy.setDefaultTargetDataSource(masterDataSource());
        proxy.setTargetDataSources(targetDataSource);
        return proxy;
    }
}
