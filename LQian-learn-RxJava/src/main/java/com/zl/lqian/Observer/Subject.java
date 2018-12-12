package com.zl.lqian.Observer;

/**
 * @Author zl
 * @Date 2018-12-12
 * @Des 被观察着
 */
public interface Subject<T extends Subject> {

    void registerObserver(ObserverMode<T> observer);    // 注册观察者

    void removeObserver(ObserverMode observer);      //删除观察者

    void notifyObservers();      //当主题改变时，调用这个方法通知所有观察者

}
