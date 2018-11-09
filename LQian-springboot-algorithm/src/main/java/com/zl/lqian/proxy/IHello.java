package com.zl.lqian.proxy;

/**
 *
 *  JDK 的 动态代理 使用条件:
 *   1）必须实现InvocationHandler接口；
 *   2）使用Proxy.newProxyInstance产生代理对象；
 *   3）被代理的对象必须要实现接口；
 *
 *  使用JDK动态代理的五大步骤：
 *   1）通过实现InvocationHandler接口来自定义自己的InvocationHandler；
 *   2）通过Proxy.getProxyClass获得动态代理类；
 *   3）通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)；
 *   4）通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入；
 *   5）通过代理对象调用目标方法；

 */

public interface IHello {

    void sayHello();
}
