package com.zl.lqian.client.interfaces;

/**
 * canal客户端
 */
public interface CanalClient {


    /**
     * 开启客户端并进行连接
     */
    void start();


    /**
     * 关闭连接
     */
    void stop();

    /**
     * 判断是否连接
     */
    boolean isRunning();
}
