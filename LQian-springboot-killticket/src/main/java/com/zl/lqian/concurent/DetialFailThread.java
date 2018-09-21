package com.zl.lqian.concurent;

import com.zl.lqian.dto.RetryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DetialFailThread {

    private static Logger logger = LoggerFactory.getLogger(DetialFailThread.class);

    //设置为单例
    private static DetialFailThread instance;

    private LinkedBlockingQueue<RetryDto> failQueue = new LinkedBlockingQueue<RetryDto>(); //阻塞队列


    private final static int THREAD_NUM = Runtime.getRuntime().availableProcessors() + 1;

    private static final int MAX_THREAD = Runtime.getRuntime().availableProcessors() << 1;

    private static final int QUEUE_CAPACITY =  Runtime.getRuntime().availableProcessors() << 10;

    private KillThreadExecutor executorService = new KillThreadExecutor(THREAD_NUM,MAX_THREAD,
            QUEUE_CAPACITY,DistributorThreadFactory.create("DetialFailThread-->process",true));

    private volatile boolean stop = true;


    private DetialFailThread(){}


    public static DetialFailThread getInstance(){

        if (instance != null){
            return instance;
        }
        return new DetialFailThread();
    }


    public static void pushQueue(RetryDto retryDto){
        getInstance().failQueue.add(retryDto);
        logger.debug(">>>>>>>>>>> kill-ticket pushQueue request url:{},param:{},headers:{},cityName:{}",retryDto.getUrl(),retryDto.getParam(),
                retryDto.getHeaders(),retryDto.getCityName());
    }

    //todo 启动监控线程
    public void start(){
        executorService.execute(new worker());
    }

class worker implements Runnable{
        @Override
        public void run() {

            /**
             * 自旋转
             */
            while (!stop){
                //从队列取
                try {
                    if (failQueue != null && failQueue.size() > 0) {
                        //注意 如果队列没有值 则会一直处于阻塞状态
                        RetryDto retryDto = getInstance().failQueue.take();
                        //TODO  其实这里是可以设置短信预警哪里出错了
                        //TODO  但是穷人买不起短信api
                        doRetary(retryDto);
                    }

                }catch (Exception e){
                    logger.error(e.getMessage(), e);
                }
                try {
                    TimeUnit.SECONDS.sleep(1000l);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }

            }
        }
    }

    public void stop(){
        stop = false;
        executorService.shutdown();
        try {
            //此方法是检测线程池关闭后 池内线程是否全部执行完毕
            if (executorService.awaitTermination(100, TimeUnit.SECONDS)) {
                //为true 说明线程已经完全结束
                 if (logger.isDebugEnabled()){
                     logger.debug("线程池任务全部结束,线程已经关闭");
                 }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    private void  doRetary(RetryDto retryDto){
        try {
            Class clazz = Class.forName("com.zl.lqian.service.impl.StationServiceImpl");
            Class [] paramType= {String.class};
            Method method = clazz.getMethod("doRetry",paramType);
            method.invoke(clazz.newInstance(),retryDto.getCityName());
        }catch (Exception e){
            e.printStackTrace();
            //这里要是继续出错了 说明可能被拉黑了
        }

    }
    public static void main(String[] args){
        System.out.println(Runtime.getRuntime().availableProcessors() << 10);
        DetialFailThread detialFailThread = new DetialFailThread();
        RetryDto retryDto = new RetryDto();
        retryDto.setCityName("上海虹桥");
        detialFailThread.doRetary(retryDto);
    }

}
