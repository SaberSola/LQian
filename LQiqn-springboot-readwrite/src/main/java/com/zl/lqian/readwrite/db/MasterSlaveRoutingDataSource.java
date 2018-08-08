package com.zl.lqian.readwrite.db;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @Author zl
 * AbstractRoutingDataSource 是spring提供的多数据源转换
 */
public class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 重点 需要根据key 切换不同的数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
