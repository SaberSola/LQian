package com.zl.lqian.readwrite.conf.ratelimt;


import com.zl.lqian.readwrite.ratelimtit.DistributedRateLimit;
import com.zl.lqian.readwrite.ratelimtit.RedisLimitBootStrap;
import com.zl.lqian.readwrite.conf.redis.RedisCacheConfig;
import com.zl.lqian.readwrite.ratelimtit.RateLimiterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

@Configuration
@Order(5 )
public class RateLimiterAutoConfiguration {


    @Bean
    public DefaultRedisScript<Long> rateLimiterLua() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<Long>();
        defaultRedisScript.setLocation(new ClassPathResource("rate_limiter.lua"));
        defaultRedisScript.setResultType(Long.class);
        System.out.println(444);
        return defaultRedisScript;
    }



    @Bean
    public RedisLimitBootStrap redisLimitBootstrap(DistributedRateLimit distributedRateLimit) {
        System.out.println(666);
        final RedisLimitBootStrap redisLimitBootStrap = new RedisLimitBootStrap(distributedRateLimit);
        return redisLimitBootStrap;
    }
}
