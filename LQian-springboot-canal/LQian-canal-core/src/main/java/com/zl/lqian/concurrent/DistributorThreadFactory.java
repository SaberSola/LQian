package com.zl.lqian.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Autho zl
 * 自定义线程工厂
 */
public class DistributorThreadFactory implements ThreadFactory {


    private static final AtomicLong THREAD_NUMBER = new AtomicLong(0);//定义产生的线程数量

    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("DistributorThread");

    /**
     * TODO 守护线程: 相当于普通线程的保姆,
     * TODO 只要jvm中任何一个非守护线程存在,
     * TODO 守护线程就会存在
     */
    private static volatile boolean daemon;// true ？守护线程 : 普通线程

    private final String namePrefix;

    private DistributorThreadFactory(final String namePrefix, final boolean daemon){
        this.namePrefix = namePrefix;
        DistributorThreadFactory.daemon = daemon;
    }
    public static ThreadFactory create(final String namePrefix, final boolean daemon) {
        return new DistributorThreadFactory(namePrefix, daemon);
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(THREAD_GROUP, runnable,
                THREAD_GROUP.getName() + "-" + namePrefix + "-" + THREAD_NUMBER.getAndIncrement());
        thread.setDaemon(daemon);
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
