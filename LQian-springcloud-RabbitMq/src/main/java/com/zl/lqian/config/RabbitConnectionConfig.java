package com.zl.lqian.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    MqProperties rabbitProperties;

    /**
     *
     * 创建连接rabbitmq的工厂
     */
    @Bean
    public ConnectionFactory rabbitConnectionFactory() throws Exception{

       // logger.info("==> custom rabbitmq connection factory, addresses: {}", rabbitProperties.host);

        RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();

        /*if (rabbitProperties.host!= null) {
            factory.setHost(rabbitProperties.host);
        }*/

        //factory.setPort(rabbitProperties.port);
        if (rabbitProperties.username != null) {
            factory.setUsername(rabbitProperties.username);
        }
        if (rabbitProperties.password!= null) {
            factory.setPassword(rabbitProperties.password);
        }
        //如果需要ssl连接的话

      /*  RabbitProperties.Ssl ssl = rabbitProperties.getSsl();
        if (ssl.isEnabled()){
            factory.setUseSSL(true);
            factory.setKeyStore(ssl.getKeyStore());
            factory.setKeyStorePassphrase(ssl.getKeyStorePassword());
            factory.setTrustStore(ssl.getTrustStore());
            factory.setTrustStorePassphrase(ssl.getTrustStorePassword());
        }*/
        if (rabbitProperties.connection_timeout != null) {
            System.out.println("设置链接的超时时间"+Integer.parseInt(rabbitProperties.connection_timeout.substring(0, rabbitProperties.connection_timeout.length() - 2)));
            factory.setConnectionTimeout(Integer.parseInt(rabbitProperties.connection_timeout.substring(0, rabbitProperties.connection_timeout.length() - 2)));
        }

        factory.afterPropertiesSet();
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(factory.getObject());
        //设置消息必须confirm
        connectionFactory.setAddresses(shuffle(rabbitProperties.address));
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);

        if (rabbitProperties.size != null) {
            connectionFactory.setChannelCacheSize(rabbitProperties.size);
        }
      /*  if (rabbitProperties.getCache().getConnection().getMode() != null) {
            connectionFactory.setCacheMode(rabbitProperties.getCache().getConnection().getMode());
        }
        if (rabbitProperties.getCache().getConnection().getSize() != null) {
            connectionFactory.setConnectionCacheSize(rabbitProperties.getCache().getConnection().getSize());
        }*/
        if (rabbitProperties.checkout_timeout != null) {
            connectionFactory.setChannelCheckoutTimeout(Integer.parseInt(rabbitProperties.checkout_timeout.substring(0, rabbitProperties.checkout_timeout.length() - 2)));
        }
        System.out.println("connectionFactory实例化完成");
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
