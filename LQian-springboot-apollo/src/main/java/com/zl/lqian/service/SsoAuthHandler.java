package com.zl.lqian.service;

import org.springframework.security.core.Authentication;

/**
 * 不同的登录方式需要实现此接口
 * 注意词注解只有唯有一个方法的时候可以使用
 * 可以用于函数式编程
 */
@FunctionalInterface
public interface SsoAuthHandler {

    public Authentication auth(Authentication authentication);
}
