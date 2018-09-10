package com.zl.lqian.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * Redis配置
 */
@Configuration
public class RedisConfiguration {


    private final Environment env;

    private final JedisConfig jedisConfig;

    @Autowired
    RedisConfiguration(final Environment env, final JedisConfig jedisConfig) {
        this.env = env;
        this.jedisConfig = jedisConfig;
    }

    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Bean
    public JedisConnectionFactory getConnectionFactory () {

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisConfig.maxIdle);
        jedisPoolConfig.setMinIdle(jedisConfig.minIdle);
        jedisPoolConfig.setMaxTotal(jedisConfig.maxActive);
        int l = Integer.parseInt(jedisConfig.maxWait.substring(0, jedisConfig.maxWait.length() - 2));
        jedisPoolConfig.setMaxWaitMillis(l);
        jpb.poolConfig(jedisPoolConfig);
        final Boolean cluster = env.getProperty("canal.redis.cluster", Boolean.class);
        if (cluster){
            return new JedisConnectionFactory(getClusterConfiguration(),jpb.build());
        }else {
            return new JedisConnectionFactory(getRedisStandaloneConfiguration(), jpb.build());
        }
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    //集群
    private RedisClusterConfiguration getClusterConfiguration() {
        Map<String, Object> source = Maps.newHashMap();
        source.put("spring.redis.cluster.nodes", env.getProperty("canal.redis.cluster.nodes"));
        source.put("spring.redis.cluster.max-redirects", env.getProperty("canal.redis.cluster.redirects"));
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }

    //单机
    private RedisStandaloneConfiguration getRedisStandaloneConfiguration(){
        RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
        rf.setDatabase(jedisConfig.database);
        rf.setHostName(jedisConfig.host);
        rf.setPort(jedisConfig.port);
        return rf;
    }
}
