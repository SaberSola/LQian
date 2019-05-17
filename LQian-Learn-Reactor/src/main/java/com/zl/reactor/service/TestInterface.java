package com.zl.reactor.service;

/**
 * @Author zl
 * @Date 2019-05-07
 * @Des ${todo}
 */
public interface TestInterface {

    void hello();

    default void init() throws Exception {
        System.out.println("zl");
    }

    void bike();
}
