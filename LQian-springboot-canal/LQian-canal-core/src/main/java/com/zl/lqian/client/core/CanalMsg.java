package com.zl.lqian.client.core;

import lombok.Data;

@Data
public class CanalMsg {

    /**
     * 指令
     */
    private String destination;
    /**
     * 数据库实例名称
     */
    private String schemaName;
    /**
     * 数据库表名称
     */
    private String tableName;
}
