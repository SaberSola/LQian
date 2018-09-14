package com.zl.lqian.config;

import com.zl.lqian.client.core.ProcessCanalClient;
import com.zl.lqian.client.interfaces.CanalClient;
import com.zl.lqian.util.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class CanalClientConfiguration {


    /**
     * 记录日志
     */
    private final static Logger logger = LoggerFactory.getLogger(CanalClientConfiguration.class);

    /**
     * 配置
     */
    @Autowired
    CanalConfig canalConfig;



    @Bean
    public CanalClient canalClient(ConfigurableApplicationContext applicationContextAware){
        logger.info("正在尝试连接 canal 客户端....");
        //TODO
        SpringBeanUtils.getInstance().setCfgContext(applicationContextAware);

        CanalClient canalClient = new ProcessCanalClient(canalConfig);
        logger.info("正在尝试开启 canal 客户端....");
        //开启 canal 客户端
        canalClient.start();
        logger.info("启动 canal 客户端成功....");
        //返回结果
        return canalClient;

    }
}
