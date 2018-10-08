package com.zl.lqian.eventbus;

import com.google.common.eventbus.Subscribe;

public class DataObserver1 {

    /**
     * 加了注解后 才能被注册
     * @param msg
     */
    @Subscribe
    public void func(String msg) {
        System.out.println("String msg: " + msg);
    }

}
