package com.zl.lqian.client.core;

import com.zl.lqian.annotation.ListenPoint;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 此class是为了监听的canal操作
 */
public class ListenerPoint {

    /**
     * target
     */
    private Object target;

    /**
     * 监听的方法和节点
     */
    private Map<Method, ListenPoint> invokeMap = new ConcurrentHashMap<>();

    //构造方法
    ListenerPoint(Object target, Method method, ListenPoint anno) {
        this.target = target;
        this.invokeMap.put(method, anno);
    }

    public Object getTarget(){return  target;}

    public Map<Method, ListenPoint> getInvokeMap() {
        return invokeMap;
    }
}
