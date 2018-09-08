package com.zl.lqian.client.core;

import com.alibaba.otter.canal.client.CanalConnector;
import com.zl.lqian.annotation.ListenPoint;
import com.zl.lqian.client.abstracts.AbstractCanalClient;
import com.zl.lqian.client.interfaces.CanalEventListener;
import com.zl.lqian.concurrent.DistributorThreadFactory;
import com.zl.lqian.config.CanalConfig;
import com.zl.lqian.util.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  初始化canalclent
 */
public class ProcessCanalClient extends AbstractCanalClient {


    /**
     * 声明一个线程池
     */
    private ThreadPoolExecutor executor;

    /**
     * 通过实现接口的监听器
     */
    protected final List<CanalEventListener> listeners = new ArrayList<>();

    /**
     * 通过注解的方式实现的监听器
     */
    private final List<ListenerPoint> annoListeners = new ArrayList<>();

    /**
     * 日志记录
     */
    private final static Logger logger = LoggerFactory.getLogger(ProcessCanalClient.class);

    protected final static int THREAD_NUM = Runtime.getRuntime().availableProcessors() + 1;

    protected static final int MAX_THREAD = Runtime.getRuntime().availableProcessors() << 1;

    public ProcessCanalClient(CanalConfig canalConfig) {
        super(canalConfig);
        executor = new ThreadPoolExecutor(THREAD_NUM, MAX_THREAD,
                120L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), DistributorThreadFactory.create("cannal->process",false));
        //初始化监听器
        initListeners();
    }

    @Override
    protected void process(CanalConnector connector, Map.Entry<String, CanalConfig.Instance> config) {

        executor.submit(factory.newTransponder(connector, config, listeners, annoListeners));
    }

    /**
     * 初始化
     */
    private void initListeners(){

        logger.debug("{}: 监听器正在初始化....",Thread.currentThread().getName());
        //开始获取监听器 注意此处或的是impl
        List<CanalEventListener> list = SpringBeanUtils.getInstance().getBeansOfType(CanalEventListener.class);
        if(list != null && list.size() > 0){
            //存入监听map
            listeners.addAll(list);
        }
        //通过注解方式
        Map<String,Object> listenMap = SpringBeanUtils.getInstance().getBeansWithAnnotation(com.zl.lqian.annotation.CanalEventListener.class);
        //TODO 这里不喜欢用双循环
        if(listenMap != null){
            for (Object target : listenMap.values()){
                //方法获取
                Method[] methods = target.getClass().getDeclaredMethods();
                if (methods != null && methods.length > 0){
                    for (Method method : methods){
                        //这里找出注解标识的方法
                        ListenPoint listenPoint = AnnotationUtils.findAnnotation(method,ListenPoint.class);
                        if (listenPoint != null){
                            annoListeners.add(new ListenerPoint(target, method, listenPoint ));
                        }
                    }
                }
            }
        }
        //监听结束
        logger.debug("{}: 监听器初始化结束",Thread.currentThread().getName());
        //这里要是没有监听初始化
        if (listeners == null && annoListeners == null ){
            logger.debug("{}: 该项目中没有任何监听的目标! ", Thread.currentThread().getName());
        }

    }
}
