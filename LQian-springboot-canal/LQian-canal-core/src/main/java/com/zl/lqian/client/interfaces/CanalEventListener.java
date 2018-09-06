package com.zl.lqian.client.interfaces;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * canal事件,table发生修改时会触发
 */
@FunctionalInterface
public interface CanalEventListener {


    /**
     * @param destination canal 指令
     * @param schemaName  库实例
     * @param tableName   表名
     * @param rowChange   从binlog获取的详细参数
     */
    void onEvent(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange);

}
