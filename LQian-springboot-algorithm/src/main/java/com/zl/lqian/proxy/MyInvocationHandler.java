package com.zl.lqian.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

public class MyInvocationHandler implements InvocationHandler {

    private Object target; //被代理的对象的实现类

    public MyInvocationHandler(Object target) {
        this.target = target;
    }


        /**
         *
         * @param proxy 代理的对象
         * @param method 方法
         * @param args 参数
         * @return
         * @throws Throwable
         */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("------插入前置通知代码-------------");
        // 执行相应的目标方法
        Object rs = method.invoke(target,args);
        System.out.println("------插入后置处理代码-------------");
        return rs;
    }
}
