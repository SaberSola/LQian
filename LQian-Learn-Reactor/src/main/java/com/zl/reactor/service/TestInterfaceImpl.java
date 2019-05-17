package com.zl.reactor.service;

/**
 * @Author zl
 * @Date 2019-05-07
 * @Des ${todo}
 */
public class TestInterfaceImpl implements TestInterface {

    @Override
    public void hello() {
        System.out.println("hello");
    }

    @Override
    public void init() throws Exception {
        System.out.println("this is init");
    }

    @Override
    public void bike() {
        System.out.println("bike");
    }
}
