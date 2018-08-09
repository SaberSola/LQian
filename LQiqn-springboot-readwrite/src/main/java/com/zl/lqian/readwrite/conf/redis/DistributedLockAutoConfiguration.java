package com.zl.lqian.readwrite.conf.redis;


import com.zl.lqian.readwrite.lock.DistributedLock;
import com.zl.lqian.readwrite.lock.RedisDistributedLock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
@Order(4)
public class DistributedLockAutoConfiguration {


    /**
     * @param redisTemplate
     * @return
     */
    @Bean
    public DistributedLock redisDistributedLock(RedisTemplate<Object, Object> redisTemplate){
        return new RedisDistributedLock(redisTemplate);
    }
}
