package com.zl.lqian.config.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


/**
 * redis 缓存配置;
 */
@Configuration
@EnableCaching//启用缓存，这个注解很重要；
@Order(3)
public class RedisCacheConfig extends CachingConfigurerSupport {

    //缓存管理器
    @Bean
    public RedisCacheManager cacheManager(JedisConnectionFactory redisConnectionFactory) {

        return RedisCacheManager.create(redisConnectionFactory);
    }


    @Autowired
    JedisConfig jedisConfig;


    /*@Autowired
    JedisConnectionFactory jedisConnectionFactory;*/

    @Bean
    public JedisConnectionFactory jedisConnectionFactory () {
        RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
        rf.setDatabase(jedisConfig.database);
        rf.setHostName(jedisConfig.host);
        rf.setPort(jedisConfig.port);
        int to = Integer.parseInt(jedisConfig.timeout.substring(0, jedisConfig.timeout.length() - 2));
        //JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        //jedisClientConfiguration.connectTimeout(Duration.ofMillis(to));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisConfig.maxIdle);
        jedisPoolConfig.setMinIdle(jedisConfig.minIdle);
        jedisPoolConfig.setMaxTotal(jedisConfig.maxActive);
        int l = Integer.parseInt(jedisConfig.maxWait.substring(0, jedisConfig.maxWait.length() - 2));
        jedisPoolConfig.setMaxWaitMillis(l);
        jpb.poolConfig(jedisPoolConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
        return jedisConnectionFactory;

    }
        @Bean
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(new FastJsonJsonRedisSerializer<>());
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(new FastJsonJsonRedisSerializer<>());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);

        RedisSerializer stringRedisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        stringRedisTemplate.setKeySerializer(stringRedisSerializer);
        stringRedisTemplate.setValueSerializer(new FastJsonJsonRedisSerializer<>());
        stringRedisTemplate.setHashKeySerializer(stringRedisSerializer);
        stringRedisTemplate.setHashValueSerializer(stringRedisSerializer);
        return stringRedisTemplate;
    }


}
