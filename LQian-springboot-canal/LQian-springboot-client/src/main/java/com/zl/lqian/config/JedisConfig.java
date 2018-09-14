package com.zl.lqian.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class JedisConfig {
    @Value("${canal.redis.host}")
    public String host;
    @Value("${canal.redis.port}")
    public int port;
    @Value("${canal.redis.database}")
    public int database;
    @Value("${canal.redis.jedis.pool.max-idle}")
    public int maxIdle;
    @Value("${canal.redis.jedis.pool.min-idle}")
    public int minIdle;
    @Value("${canal.redis.jedis.pool.max-active}")
    public int maxActive;
    @Value("${canal.redis.jedis.pool.max-wait}")
    public String maxWait;
    @Value("${canal.redis.timeout}")
    public String timeout;

}

