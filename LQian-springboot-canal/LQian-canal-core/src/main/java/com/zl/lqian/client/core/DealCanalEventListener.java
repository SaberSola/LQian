package com.zl.lqian.client.core;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.abstracts.option.AbstractDBOption;
import com.zl.lqian.client.interfaces.CanalEventListener;

/**
 *
 * 处理canal 监听器
 * 只针对于接口方式的实现
 *
 */
@SuppressWarnings("all")
public class DealCanalEventListener implements CanalEventListener {


    /**
     * 传入头节点
     */
    private AbstractDBOption header;


    /**
     *  默认构造方法
     */
    public DealCanalEventListener(AbstractDBOption... dbOptions){
        //声明一个临时变量
        AbstractDBOption temp = null;
        for (AbstractDBOption dbOption : dbOptions){
            if (temp != null){
                temp.setNext(dbOption);
            }else {
                this.header = dbOption;
            }
            temp = dbOption;
        }
    }




    /**
     *
     * @param destination canal 指令
     * @param schemaName  库实例
     * @param tableName   表名
     * @param rowChange   从binlog获取的详细参数
     */
    @Override
    public void onEvent(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange) {
        this.header.doOption(destination, schemaName, tableName, rowChange);

    }
}
