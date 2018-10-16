package com.zl.lqian.utils;

public class SeckillKey extends BasePrefix {

    private SeckillKey(String prefix) {
        super(prefix);
    }

    public static SeckillKey isGoodsOver = new SeckillKey("go");
}
