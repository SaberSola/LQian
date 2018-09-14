package com.zl.demo.core;

/**
 * @Author zl
 * 使用netty的小工程
 * 根据twitter的雪花算法生成的唯一id
 */
public interface Server {

    void start();

    void shutdown();
}
