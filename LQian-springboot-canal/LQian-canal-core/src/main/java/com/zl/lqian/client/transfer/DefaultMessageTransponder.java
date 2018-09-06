package com.zl.lqian.client.transfer;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.annotation.ListenPoint;
import com.zl.lqian.client.abstracts.AbstractBasicMessageTransponder;
import com.zl.lqian.client.core.CanalMsg;
import com.zl.lqian.client.core.ListenerPoint;
import com.zl.lqian.client.interfaces.CanalEventListener;
import com.zl.lqian.config.ConfigProperties;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 消息转换
 */
public class DefaultMessageTransponder extends AbstractBasicMessageTransponder {


    public DefaultMessageTransponder(CanalConnector connector, Map.Entry<String, ConfigProperties.Instance> config,
                                     List<CanalEventListener> listeners, List<ListenerPoint> annoListeners) {
        super(connector, config, listeners, annoListeners);
    }


    /**
     * 断言监听过滤规则
     * @param destination
     * @param schemaName
     * @param tableName
     * @param eventType
     * @return
     */
    @Override
    protected Predicate<Map.Entry<Method, ListenPoint>> getAnnotationFilter(String destination, String schemaName, String tableName, CanalEntry.EventType eventType) {

        //查看指令是否正确
        Predicate<Map.Entry<Method, ListenPoint>> df = methodListenPointEntry -> StringUtils.isEmpty(methodListenPointEntry.getValue().destination())
                || methodListenPointEntry.getValue().destination().equals(destination) || destination == null;


        //看看数据库实例名是否一样
        Predicate<Map.Entry<Method, ListenPoint>> sf = e -> e.getValue().schema().length == 0
                || Arrays.stream(e.getValue().schema()).anyMatch(s -> s.equals(schemaName)) || schemaName == null;

        //看看表名是否一样
        Predicate<Map.Entry<Method, ListenPoint>> tf = e -> e.getValue().table().length == 0
                || Arrays.stream(e.getValue().table()).anyMatch(t -> t.equals(tableName)) || tableName == null;

        //类型一致？
        Predicate<Map.Entry<Method, ListenPoint>> ef = e -> e.getValue().eventType().length == 0
                || Arrays.stream(e.getValue().eventType()).anyMatch(ev -> ev == eventType) || eventType == null;


        return df.and(sf).and(tf).and(ef);
    }

    /**
     * 获取处理的参数
     * @param method
     * @param canalMsg
     * @param rowChange
     * @return
     */
    @Override
    protected Object[] getInvokeArgs(Method method, CanalMsg canalMsg, CanalEntry.RowChange rowChange) {

        return Arrays.stream(method.getParameterTypes())
                .map(p -> p == CanalMsg.class ? canalMsg : p == CanalEntry.RowChange.class ? rowChange : null)
                .toArray();
    }

    @Override
    protected List<CanalEntry.EntryType> getIgnoreEntryTypes() {
        return Arrays.asList(CanalEntry.EntryType.TRANSACTIONBEGIN, CanalEntry.EntryType.TRANSACTIONEND, CanalEntry.EntryType.HEARTBEAT);
    }
}
