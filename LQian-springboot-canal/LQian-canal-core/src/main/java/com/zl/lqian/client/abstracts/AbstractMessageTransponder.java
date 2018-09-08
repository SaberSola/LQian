package com.zl.lqian.client.abstracts;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.zl.lqian.client.core.ListenerPoint;
import com.zl.lqian.client.interfaces.CanalEventListener;
import com.zl.lqian.client.interfaces.MessageTransponder;
import com.zl.lqian.config.CanalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractMessageTransponder implements MessageTransponder {

    /**
     * canal连接器
     */
    private final CanalConnector connector;

    /**
     * 连接配置
     */
    protected final CanalConfig.Instance config;

    /**
     * canal 服务指令
     */
    protected final String destination;

    /**
     * 实现接口的 canal 监听器(上：表内容，下：表结构)
     */
    protected final List<CanalEventListener> listeners = new ArrayList<>();


    /**
     * 通过注解方式的 canal 监听器
     */
    protected final List<ListenerPoint> annoListeners = new ArrayList<>();

    /**
     * canal 客户端的运行状态
     */
    private volatile boolean running = true;

    /**
     * 重试次数
     */
    private volatile AtomicInteger atomicCount ;

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractMessageTransponder.class);


    public AbstractMessageTransponder(CanalConnector connector, Map.Entry<String, CanalConfig.Instance> config,
                                      List<CanalEventListener> listeners, List<ListenerPoint> annoListeners) {

        Objects.requireNonNull(connector,"连接器不能为空");
        Objects.requireNonNull(config,"连接器不能为空");

        this.connector = connector;
        this.config = config.getValue();
        this.destination = config.getKey();
        atomicCount = new AtomicInteger(this.config.getRetryCount());
        if (listeners != null) {
            this.listeners.addAll(listeners);
        }
        if (annoListeners != null){
            this.annoListeners.addAll(annoListeners);
        }

    }
    @Override
    public void run() {
        //错误重试的次数
        //心跳时间
        final long interval = config.getAcquireInterval();
        //当前线程的名称
        String threadName = Thread.currentThread().getName();
        while (running && !Thread.currentThread().isInterrupted()){
            //TODO 重点
            try {
                //接收canal服务的消息
                Message message = connector.getWithoutAck(config.getBatchSize());
                long batchId = message.getId();
                //消息数目
                int size = message.getEntries().size();
                logger.debug("当前线程名称name:{},从canal服务器获取的消息》》》 数:{}",threadName,size);
                //如果没有消息
                if (batchId == -1 || size == 0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("{}: 没有任何消息啊，我休息{}毫秒", threadName, interval);
                    }
                    Thread.sleep(interval);
                }else {
                   //TODO 重点消息处理
                    distributeEvent(message);
                }
            }catch (CanalClientException e){
                //开始retry count-1
                atomicCount.decrementAndGet();
                logger.error(threadName + ": 发生错误!! ", e);
                try {
                    //等待时间
                    Thread.sleep(interval);
                } catch (InterruptedException e1) {
                    atomicCount.set(0);
                }
            }catch (InterruptedException e) {
                //线程中止处理
                atomicCount.set(0);
                connector.rollback();
            }finally {
                if (atomicCount.get() <= 0) {
                    //停止 canal 客户端
                    stop();
                    logger.info("{}: canal 客户端已停止... ", Thread.currentThread().getName());
                }
            }
        }
        stop();
        logger.info("{}: canal 客户端已停止. ", Thread.currentThread().getName());
    }

    /**
     * 消息处理
     * @param message
     */
    protected abstract void distributeEvent(Message message);

    /**
     * stop线程
     */
    void stop() {
        running = false;
    }

}
