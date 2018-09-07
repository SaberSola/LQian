package com.zl.lqian.client.abstracts.option;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.interfaces.IDBOption;

/**
 *
 *数据库操作抽象类
 */
public abstract class AbstractDBOption implements IDBOption {


    /**
     * 操作类型
     */
    protected CanalEntry.EventType eventType;

    /**
     * 下一个节点
     */
    protected  AbstractDBOption next;


    /**
     * 构造方法
     */
    public AbstractDBOption() {
        this.setEventType();
    }

    //进行类型设置
    protected abstract void setEventType();


    /**
     * 设置下一个节点
     * @param next
     */
    public void setNext(AbstractDBOption next) {
        this.next = next;
    }

    /**
     * 设置责任链处理
     * @param destination canal 指令
     * @param schemaName  库实例
     * @param tableName   表名称
     * @param rowChange   binlog数据
     */
    @Override
    public void doOption(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange) {

        if (eventType.equals(rowChange.getEventType())){
            this.doOption(destination, schemaName, tableName, rowChange);
        }else {
            if (this.next == null){
                return;
            }
            this.next.doOption(destination, schemaName, tableName, rowChange);
        }
    }
}
