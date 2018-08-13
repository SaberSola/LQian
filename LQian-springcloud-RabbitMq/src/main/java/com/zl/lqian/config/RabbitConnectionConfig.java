package com.zl.lqian.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * rabbitConnection配置
 * @Author zl
 *
 */

@Configuration
@Order(1)
public class RabbitConnectionConfig {

    private Logger logger = LoggerFactory.getLogger(RabbitConnectionConfig.class);

    /**
     *
     * 创建连接rabbitmq的工厂
     */
    @Bean
    public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties rabbitProperties) throws Exception{

        logger.info("==> custom rabbitmq connection factory, addresses: {}", rabbitProperties.getAddresses());

        RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();

        if (rabbitProperties.determineHost() != null) {
            factory.setHost(rabbitProperties.determineHost());
        }

        factory.setPort(rabbitProperties.determinePort());
        if (rabbitProperties.determineUsername() != null) {
            factory.setUsername(rabbitProperties.determineUsername());
        }
        if (rabbitProperties.determinePassword() != null) {
            factory.setPassword(rabbitProperties.determinePassword());
        }
        if (rabbitProperties.determineVirtualHost() != null) {
            factory.setVirtualHost(rabbitProperties.determineVirtualHost());
        }
        //如果需要ssl连接的话

        RabbitProperties.Ssl ssl = rabbitProperties.getSsl();
        if (ssl.isEnabled()){
            factory.setUseSSL(true);
            factory.setKeyStore(ssl.getKeyStore());
            factory.setKeyStorePassphrase(ssl.getKeyStorePassword());
            factory.setTrustStore(ssl.getTrustStore());
            factory.setTrustStorePassphrase(ssl.getTrustStorePassword());
        }
        if (rabbitProperties.getConnectionTimeout() != null) {
            System.out.println("设置链接的超时时间"+Integer.parseInt(String.valueOf(rabbitProperties.getConnectionTimeout().getSeconds())));
            factory.setConnectionTimeout(Integer.parseInt(String.valueOf(rabbitProperties.getConnectionTimeout().getSeconds())));
        }

        factory.afterPropertiesSet();
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(factory.getObject());
        //设置消息必须confirm
        connectionFactory.setAddresses(shuffle(rabbitProperties.determineAddresses()));
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);

        if (rabbitProperties.getCache().getChannel().getSize() != null) {
            connectionFactory.setChannelCacheSize(rabbitProperties.getCache().getChannel().getSize());
        }
        if (rabbitProperties.getCache().getConnection().getMode() != null) {
            connectionFactory.setCacheMode(rabbitProperties.getCache().getConnection().getMode());
        }
        if (rabbitProperties.getCache().getConnection().getSize() != null) {
            connectionFactory.setConnectionCacheSize(rabbitProperties.getCache().getConnection().getSize());
        }
        if (rabbitProperties.getCache().getChannel().getCheckoutTimeout() != null) {

            connectionFactory.setChannelCheckoutTimeout(Integer.valueOf(String.valueOf(rabbitProperties.getCache().getChannel().getCheckoutTimeout().getSeconds())));
        }
        return connectionFactory;
    }


    String shuffle(String addresses) {
        String[] addrArr = StringUtils.commaDelimitedListToStringArray(addresses);
        List<String> addrList = Arrays.asList(addrArr);
        Collections.shuffle(addrList);

        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iter = addrList.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(iter.next());
            if (iter.hasNext()) {
                stringBuilder.append(",");
            }
        }

        logger.info("==> rabbitmq shuffle addresses: {}", stringBuilder);

        return stringBuilder.toString();
    }

}
