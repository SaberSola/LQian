package com.zl.reactor.service;

/**
 * @Author zl
 * @Date 2019-05-07
 * @Des ${todo}
 */
public class Main {

    public static void main(String [] args) throws Exception{
        TestInterface testInterface = new TestInterfaceImpl();
        testInterface.hello();
        testInterface.init();
    }
}
