package com.zl.lqian.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties
public class MqProperties {

    @Value("${spring.rabbitmq.host}")
    public String host;

    @Value("${spring.rabbitmq.port}")
    public Integer port;

    @Value("${spring.rabbitmq.username}")
    public String username;

    @Value("${spring.rabbitmq.password}")
    public String password;

    @Value("${spring.rabbitmq.connection-timeout}")
    public String connection_timeout;

    @Value("${spring.rabbitmq.template.receive-timeout}")
    public String receive_timeout;

    @Value("${spring.rabbitmq.listener.simple.concurrency}")
    public Integer concurrency;

    @Value("${spring.rabbitmq.listener.simple.max-concurrency}")
    public Integer max_concurrency;

    @Value("${spring.rabbitmq.listener.simple.acknowledge-mode}")
    public String  acknowledge_mode;

    @Value("${spring.rabbitmq.listener.simple.retry.enabled}")
    public Boolean  enabled;

    @Value("${spring.rabbitmq.cache.channel.size}")
    public Integer size;

    @Value("${spring.rabbitmq.cache.channel.checkout-timeout}")
    public String  checkout_timeout;


}
