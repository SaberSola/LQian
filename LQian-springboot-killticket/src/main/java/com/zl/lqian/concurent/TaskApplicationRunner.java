package com.zl.lqian.concurent;

import com.zl.lqian.configuration.TicketConfig;
import com.zl.lqian.service.MonterTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
/**
 * 启动完成后 执行此runner
 */
@Component
public class TaskApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TaskApplicationRunner.class);

    private MonterTicketService monterTicketService;

    private TicketConfig ticketConfig;

    private ThreadPoolTaskScheduler executorService;

    private KillThreadExecutor threadExecutor;

    private static volatile  boolean stop = true;
    
    
    public static void stop(){
        stop = false;
    }
    
    @Autowired
    public TaskApplicationRunner(final MonterTicketService service ,
                                 final TicketConfig ticketConfig,
                                 final ThreadPoolTaskScheduler executorService){
        this.monterTicketService = service;
        this.ticketConfig = ticketConfig;
        this.executorService = executorService;
        this.threadExecutor = new KillThreadExecutor(10,20,100,DistributorThreadFactory
        .create("killinf-process",false));
        //TODO 这里启动守护线程
        DetialFailThread.getInstance().start();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("您选择的模式mode:{}",ticketConfig.mode);
        CronTrigger trigger = new CronTrigger("* * 6-23 * * ?");
        switch (ticketConfig.mode){
            case "1":
                logger.info("使用极速模式开始抢票");
                executorService.schedule(()->{
                    monterTicketService.monitor();
                },trigger);
                break;
            case "2":
                //注意这里就是开启线程抢票
                threadExecutor.submit(()->{
                    while (stop){
                        monterTicketService.monitor();
                    }
                });
                break;
            case "3":
                //这里多线程疯狂刷接口
                for (int i= 0; i< 10; i++) {
                    threadExecutor.submit(() -> {
                        while (stop) {
                            monterTicketService.monitor();
                        }
                    });
                }
                break;
            case "4":
                logger.info("使用 无脑下单模式(不进行是否有票的监控，直接对符合要求的车次进行下单) 开始抢票");
                monterTicketService.noBrainPlaceOrder();
                break;
            default:
                logger.info("请在配置文件中配置好抢票的形式");
                break;

        }
        logger.info("抢票线程已经正式启动");
    }
}
