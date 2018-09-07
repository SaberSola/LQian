package com.zl.lqian.client.core;

import com.alibaba.otter.canal.client.CanalConnector;
import com.zl.lqian.client.abstracts.AbstractCanalClient;
import com.zl.lqian.client.interfaces.CanalEventListener;
import com.zl.lqian.concurrent.DistributorThreadFactory;
import com.zl.lqian.config.ConfigProperties;
import com.zl.lqian.util.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
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

    public ProcessCanalClient(ConfigProperties canalConfig) {
        super(canalConfig);
        executor = new ThreadPoolExecutor(THREAD_NUM, MAX_THREAD,
                120L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), DistributorThreadFactory.create("cannal->process",false));
        //初始化监听器
        initListeners();
    }

    @Override
    protected void process(CanalConnector connector, Map.Entry<String, ConfigProperties.Instance> config) {

        executor.submit(factory.newTransponder(connector, config, listeners, annoListeners));
    }

    /**
     * 初始化
     */
    private void initListeners(){

        logger.debug("{}: 监听器正在初始化....",Thread.currentThread().getName());
        //开始获取监听器
        List<CanalEventListener> list = SpringBeanUtils.getInstance().getBeansOfType(CanalEventListener.class);
        if(list != null && list.size() > 0){
            //存入监听map
            listeners.addAll(list);
        }
        //通过注解方式


    }
}
