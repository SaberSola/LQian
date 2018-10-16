package com.zl.lqian.utils;

public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getSeckillOrderByUidGid = new OrderKey("seckill");
}
