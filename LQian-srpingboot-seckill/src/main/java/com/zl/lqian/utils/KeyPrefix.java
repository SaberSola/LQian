package com.zl.lqian.utils;

/**
 * 所有key的前缀
 */
public interface  KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
