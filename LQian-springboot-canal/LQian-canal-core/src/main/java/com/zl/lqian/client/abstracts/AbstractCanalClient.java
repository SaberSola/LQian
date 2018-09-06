package com.zl.lqian.client.abstracts;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.zl.lqian.client.interfaces.CanalClient;
import com.zl.lqian.client.interfaces.TransponderFactory;
import com.zl.lqian.client.transfer.DefaultMessageTransponder;
import com.zl.lqian.config.ConfigProperties;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractCanalClient implements CanalClient {

    /**
     * canal 客户端状态
     */
    private volatile boolean running;

    /**
     * canal配置
     */
    private ConfigProperties config;

    /**
     * 转换工厂
     */
    private TransponderFactory factory;


    protected AbstractCanalClient(ConfigProperties configProperties){

        Objects.requireNonNull(configProperties, "canalConfig 不能为空!");
        this.config = configProperties;
        this.factory = (connector, config,listeners, annoListeners)->
            new DefaultMessageTransponder(connector, config, listeners, annoListeners);
    }

    /**
     * 启动客户端
     */
    @Override
    public void start() {

        Map<String, ConfigProperties.Instance> instanceMap = getConfig();
        //开启链接
        for (Map.Entry<String, ConfigProperties.Instance> instanceEntry : instanceMap.entrySet()) {
            process(processInstanceEntry(instanceEntry), instanceEntry);
        }
    }

    /**
     * 初始化链接
     * @param connector
     * @param config
     */
    protected abstract void process(CanalConnector connector, Map.Entry<String, ConfigProperties.Instance> config);

    /**
     * TODO 处理链接实例
     * @param instanceEntry
     * @return
     */

    private CanalConnector processInstanceEntry(Map.Entry<String, ConfigProperties.Instance> instanceEntry) {

        return null;
    }
    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    /**
     * 获取配置
     */
    protected Map<String, ConfigProperties.Instance> getConfig(){
        ConfigProperties configs = config;
        Map<String, ConfigProperties.Instance> instanceMap;
        if (configs != null && (instanceMap = configs.getInstances()) != null && !instanceMap.isEmpty()) {
            //返回配置实例
            return configs.getInstances();
        } else {
            throw new CanalClientException("无法解析 canal 的连接信息，请联系开发人员!");
        }
    }
}
