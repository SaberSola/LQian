package com.zl.lqian.client.interfaces;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * 数据库操作
 */
public interface IDBOption {


    /**
     *
     * @param destination canal 指令
     * @param schemaName  库实例
     * @param tableName   表名称
     * @param rowChange   binlog数据
     */
    void doOption(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange);
}
