package com.lqian.design.JdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler{

    private Object target ;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName());
        System.err.println("1.进入代理++++++++++++++++(这里写一些公用的信息及代码!)");

        Object object =  method.invoke(target,args);
        System.err.println("1.结束代理++++++++++++++++(这里写一些公用的信息及代码!)");
        return object;
    }

    public Object getJDKProxy(Object targetObject){
        this.target = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }
}