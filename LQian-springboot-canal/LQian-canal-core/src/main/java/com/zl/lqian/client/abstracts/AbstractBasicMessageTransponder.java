package com.zl.lqian.client.abstracts;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.zl.lqian.annotation.ListenPoint;
import com.zl.lqian.client.core.CanalMsg;
import com.zl.lqian.client.core.ListenerPoint;
import com.zl.lqian.client.exception.CanalClientException;
import com.zl.lqian.client.interfaces.CanalEventListener;
import com.zl.lqian.config.CanalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 处理消息类
 */
public abstract class AbstractBasicMessageTransponder extends AbstractMessageTransponder {


    private final static Logger logger = LoggerFactory.getLogger(AbstractBasicMessageTransponder.class);

    public AbstractBasicMessageTransponder(CanalConnector connector, Map.Entry<String, CanalConfig.Instance> config,
                                           List<CanalEventListener> listeners, List<ListenerPoint> annoListeners) {
        super(connector, config, listeners, annoListeners);
    }


    /**
     * 处理消息
     * @param message
     */
    @Override
    protected void distributeEvent(Message message) {
        //获取消息实体
        List<CanalEntry.Entry> entries =  message.getEntries();
        for (CanalEntry.Entry entry : entries) {
            //忽略实体类型
            List<CanalEntry.EntryType> ignoreEntryTypes = getIgnoreEntryTypes();
            if (ignoreEntryTypes != null
                    && ignoreEntryTypes.stream().anyMatch(t -> entry.getEntryType() == t)) {
                continue;//TODO continue关键字不能用 stream.forEach()
            }
            //rowChange 是canal服务端改变的信息
            CanalEntry.RowChange rowChange;
            try {
                //这里获取转换信息的实体
                rowChange =  CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            }catch (Exception e){
                throw new CanalClientException("信息转换出错",e.getCause());
            }
            //处理数据
            distributeByAnnotation(destination,
                    entry.getHeader().getSchemaName(),
                    entry.getHeader().getTableName(), rowChange);
            //处理监听信息
            distributeByImpl(destination,
                    entry.getHeader().getSchemaName(),
                    entry.getHeader().getTableName(), rowChange);
        }
    }

    /**
     *
     * @param destination
     * @param schemaName
     * @param tableName
     * @param rowChange
     */
    protected void distributeByAnnotation(String destination,
                                          String schemaName,
                                          String tableName,
                                          CanalEntry.RowChange rowChange){
        //开始路由分发注解监听
        if (!CollectionUtils.isEmpty(annoListeners)){
            annoListeners.forEach(listenerPoint -> {
                listenerPoint.getInvokeMap().entrySet()
                        .stream().filter(getAnnotationFilter(destination, schemaName, tableName, rowChange.getEventType()))
                        .forEach(methodListenPointEntry -> {
                            Method method = methodListenPointEntry.getKey();
                            /**
                             * setAccessible(true) 无视了java的规则
                             * 也使得反射可以调用private方法
                             */
                            method.setAccessible(true);//获取私有方法
                            try {
                                CanalMsg canalMsg = new CanalMsg();
                                canalMsg.setDestination(destination);
                                canalMsg.setSchemaName(schemaName);
                                canalMsg.setTableName(tableName);
                                //获取参数
                                Object[] args = getInvokeArgs(method, canalMsg, rowChange);
                                //反射方法执行
                                method.invoke(listenerPoint.getTarget(),args);
                            }catch (Exception e){
                                logger.error("线程:{}名注解监听器下发路由错误：错误类:{},方法名:{}",
                                        Thread.currentThread().getName(),
                                        listenerPoint.getTarget().getClass().getName(), method.getName());
                            }
                        });

            });
        }
    }

    /**
     * 处理监听消息
     * @param destination
     * @param schemaName
     * @param tableName
     * @param rowChange
     */
    protected void distributeByImpl(String destination,
                                    String schemaName,
                                    String tableName,
                                    CanalEntry.RowChange rowChange) {
        if (listeners != null) {
            for (CanalEventListener listener : listeners) {
                listener.onEvent(destination, schemaName, tableName, rowChange);
            }
        }
    }

    /**
     * 判断注解方式le
     * @param destination
     * @param schemaName
     * @param tableName
     * @param eventType
     * @return
     */
    protected abstract Predicate<Map.Entry<Method, ListenPoint>> getAnnotationFilter(String destination, String schemaName, String tableName, CanalEntry.EventType eventType);


    /**
     *  获取参数
     * @param method
     * @param canalMsg
     * @param rowChange
     * @return
     */
    protected abstract Object[] getInvokeArgs(Method method, CanalMsg canalMsg, CanalEntry.RowChange rowChange);
    /**
     * 忽略处理的类型
     */
    protected List<CanalEntry.EntryType> getIgnoreEntryTypes() {
        return Collections.emptyList();
    }
}
