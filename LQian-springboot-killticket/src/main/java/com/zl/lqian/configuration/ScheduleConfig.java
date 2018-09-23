package com.zl.lqian.configuration;

import com.zl.lqian.concurent.DistributorThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    protected final static int THREAD_NUM = Runtime.getRuntime().availableProcessors() + 1;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService taskExecutor() {
        /**
         * scheduledThread线程的特点：
         * 1.支持定时以及周期性执行任务的需求
         * 2.线程池大小固定
         * 3.创建的线程不会被销毁。（其实是因为内部使用的队列和执行策略造成了不同的线程池）
         */
        return Executors.newScheduledThreadPool(THREAD_NUM,
                DistributorThreadFactory.create("killticket->process",false));
    }

    /**
     *
     * 暂时使用这个
     * @return
     */

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }
}
