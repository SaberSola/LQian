package com.zl.lqian.Observer;

/**
 * @Author zl
 * @Date 2018-12-12
 * @Des 观察者
 */
public interface ObserverMode<T extends Subject> {


    void update(T t); //当需要改变战术时将新的战术通知给对应当球员
}
