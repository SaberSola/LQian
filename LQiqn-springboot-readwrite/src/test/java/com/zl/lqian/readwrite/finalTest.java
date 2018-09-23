package com.zl.lqian.readwrite;

import com.zl.lqian.readwrite.entity.Order;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class finalTest {

    private static final Order a = new Order();

    public static void main(String[] args){
        a.setUserId("12");
        a.setCreateTime(new Date());

    }
}
