package com.zl.lqian.proxy;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * 使用JDK动态代理的五大步骤:
 * 1.通过实现InvocationHandler接口来自定义自己的InvocationHandler;
 * 2.通过Proxy.getProxyClass获得动态代理类
 * 3.通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)
 * 4.通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入
 * 5.通过代理对象调用目标方法
 */
public class MyProxyTest {

    @Test
    public  void main()
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        // =========================第一种==========================
        // 1、生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取动态代理的类
        Class proxyClazz  = Proxy.getProxyClass(IHello.class.getClassLoader(),IHello.class);
/*
        // 3、获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);

        //创建动态
        // 4、通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        IHello iHello1 = (IHello) constructor.newInstance(new MyInvocationHandler(new HelloImpl()));
        // 5、通过代理对象调用目标方法
        iHello1.sayHello();*/

        // =========================第二种==========================
        IHello iHello  = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),new Class[]{IHello.class},new MyInvocationHandler(new HelloImpl()));
        iHello.sayHello();
    }
}
