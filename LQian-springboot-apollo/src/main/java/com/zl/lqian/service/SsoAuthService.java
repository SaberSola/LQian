package com.zl.lqian.service;



@FunctionalInterface
public interface SsoAuthService<T> {


    /**
     * 根据不同的客户端返回不同的实例
     * @param clientId
     * @return
     * @throws Throwable
     */
    Class<T> factoryOf(String clientId);
}
