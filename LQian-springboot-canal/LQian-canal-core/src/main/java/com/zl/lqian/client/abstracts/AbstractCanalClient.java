package com.zl.lqian.client.abstracts;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.zl.lqian.client.interfaces.CanalClient;
import com.zl.lqian.client.interfaces.TransponderFactory;
import com.zl.lqian.client.transfer.DefaultMessageTransponder;
import com.zl.lqian.config.CanalConfig;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
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
    private CanalConfig config;

    /**
     * 转换工厂
     */
    protected TransponderFactory factory;


    protected AbstractCanalClient(CanalConfig canalConfig){

        Objects.requireNonNull(canalConfig, "canalConfig 不能为空!");
        this.config = canalConfig;
        this.factory = (connector, config,listeners, annoListeners)->
            new DefaultMessageTransponder(connector, config, listeners, annoListeners);
    }

    /**
     * 启动客户端
     */
    @Override
    public void start() {

        Map<String, CanalConfig.Instance> instanceMap = getConfig();
        //开启链接
        for (Map.Entry<String, CanalConfig.Instance> instanceEntry : instanceMap.entrySet()) {
            process(processInstanceEntry(instanceEntry), instanceEntry);
        }
    }

    /**
     * 初始化链接
     * @param connector
     * @param config
     */
    protected abstract void process(CanalConnector connector, Map.Entry<String, CanalConfig.Instance> config);

    /**
     * TODO 处理链接实例
     * @param instanceEntry
     * @return
     */

    private CanalConnector processInstanceEntry(Map.Entry<String, CanalConfig.Instance> instanceEntry) {

        //配置
        CanalConfig.Instance instance = instanceEntry.getValue();
        //声明连接
        CanalConnector connector;

        //这里需要判断是否是集群模式
        if (instance.isClusterEnabled()){
            //这里要是集群的话
            List<SocketAddress> addresses = new ArrayList<>();
            for (String s : instance.getZookeeperAddress()){

                String[] entry = s.split(":");
                if(entry.length > 2){
                    if (entry.length != 2) {
                        throw new CanalClientException("zookeeper 地址格式不正确，应该为 ip:port....:" + s);
                    }
                    //若符合设定规则，先加入集合
                    addresses.add(new InetSocketAddress(entry[0], Integer.parseInt(entry[1])));
                }
            }
            //要是集群的话
            connector = CanalConnectors.newClusterConnector(addresses, instanceEntry.getKey(),
                    instance.getUserName(), instance.getPassword());
        }else {
            //这里表示要是不是集群的话
            connector = CanalConnectors.newSingleConnector(new InetSocketAddress(instance.getHost(), instance.getPort()),
                    instanceEntry.getKey(), instance.getUserName(), instance.getPassword());
        }

        //开启链接
        connector.connect();
        //判断是不是含有过滤规则
        if (!StringUtils.isEmpty(instance.getFilter())){
            //canal链接订阅包含
            connector.subscribe(instance.getFilter());
        }else {
            connector.subscribe();
        }

        //TODO 目前不了解链接反转的作用待研究
        connector.rollback();
        return connector;
    }
    @Override
    public void stop() {
        setRunning(false);
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    /**
     * 获取配置
     */
    protected Map<String, CanalConfig.Instance> getConfig(){
        CanalConfig configs = config;
        Map<String, CanalConfig.Instance> instanceMap;
        if (configs != null && (instanceMap = configs.getInstances()) != null && !instanceMap.isEmpty()) {
            //返回配置实例
            return configs.getInstances();
        } else {
            throw new CanalClientException("无法解析 canal 的连接信息，请联系开发人员!");
        }
    }



    private void setRunning(boolean running) {
        this.running = running;
    }
}
