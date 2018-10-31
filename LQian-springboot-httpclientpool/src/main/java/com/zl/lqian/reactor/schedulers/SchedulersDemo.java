package com.zl.lqian.reactor.schedulers;

import org.junit.Test;
import reactor.core.scheduler.Schedulers;

public class SchedulersDemo {


    /**
     * 调度器（Schedulers）
     */
    @Test
    public void testOne(){

        //当前线程
        Schedulers.immediate();
        //可重用的单线程
        Schedulers.single();
        //Schedulers.newSingle(); 每一次都创建新县城

        //弹性线程池它根据需要创建一个线程池，
        // 重用空闲线程。线程池如果空闲时间过长
        // （默认为 60s）就会被废弃。对于 I/O 阻塞的场景比较适用
        Schedulers.elastic();

        //固定大小的线程池  所创建线程池的大小与 CPU 个数等同。
        Schedulers.parallel();

    }
}
