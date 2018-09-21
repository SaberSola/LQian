package com.zl.lqian.concurent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池
 * 业务中处里千万不要new Thread
 */
public class KillThreadExecutor  extends ThreadPoolExecutor {

    private static final Integer DEFAULT_MIN_THREADS = 20; //默认线程数

    public static final Integer DEFAULT_MAX_THREADS = 200; // 最大线程数.

    public static final Integer DEFAULT_MAX_IDLE_TIME = 60 * 1000; // 1分钟

    protected AtomicInteger submittedTasksCount; //正在处理的线程数

    private int maxSubmittedTaskCount;  // 最大允许同时处理的任务数.


    public KillThreadExecutor() {
        this(DEFAULT_MIN_THREADS, DEFAULT_MAX_THREADS);
    }

    public KillThreadExecutor(int coreThread, int maxThreads) {
        this(coreThread, maxThreads, maxThreads);
    }

    public KillThreadExecutor(int coreThread, int maxThreads, long keepAliveTime, TimeUnit unit) {
        this(coreThread, maxThreads, keepAliveTime, unit, maxThreads);
    }

    public KillThreadExecutor(int coreThreads, int maxThreads, int queueCapacity) {
        this(coreThreads, maxThreads, queueCapacity, Executors.defaultThreadFactory());
    }

    public KillThreadExecutor(int coreThreads, int maxThreads, int queueCapacity, ThreadFactory threadFactory) {
        this(coreThreads, maxThreads, DEFAULT_MAX_IDLE_TIME, TimeUnit.MILLISECONDS, queueCapacity, threadFactory);
    }

    public KillThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit, int queueCapacity) {
        this(coreThreads, maxThreads, keepAliveTime, unit, queueCapacity, Executors.defaultThreadFactory());
    }

    public KillThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit,
                                  int queueCapacity, ThreadFactory threadFactory) {
        this(coreThreads, maxThreads, keepAliveTime, unit, queueCapacity, threadFactory, new AbortPolicy());
    }



    public KillThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit,
                                  int queueCapacity, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(coreThreads, maxThreads, keepAliveTime, unit, new LinkedBlockingDeque<>(), threadFactory, handler);

        submittedTasksCount = new AtomicInteger(0);

        // 最大并发任务限制： 队列buffer数 + 最大线程数.
        maxSubmittedTaskCount = queueCapacity + maxThreads;
    }


    public void execute(Runnable command){

        int count = submittedTasksCount.incrementAndGet();

        /*
         * TODO 超过最大的并发任务限制，进行 reject.
         * 		依赖的LinkedTransferQueue没有长度限制，因此这里进行控制.
         */
        if (count > maxSubmittedTaskCount) {
            submittedTasksCount.decrementAndGet();
            getRejectedExecutionHandler().rejectedExecution(command, this);
        }

        try {
            super.execute(command);
        } catch (RejectedExecutionException rx) {
            //TODO 这里需要解决拒绝后如何处理
        }
    }

    public int getSubmittedTasksCount() {
        return this.submittedTasksCount.get();
    }

    public int getMaxSubmittedTaskCount() {
        return maxSubmittedTaskCount;
    }

    protected void afterExecute(Runnable r, Throwable t) {
        submittedTasksCount.decrementAndGet();
    }
}
