package com.zl.lqian.readwrite.ratelimtit;

import com.zl.lqian.readwrite.common.RedisLimiteConfig;
import com.zl.lqian.readwrite.common.RedisLimiteConfigList;
import com.zl.lqian.readwrite.common.utils.SpringBeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class RedisLimitBootStrap implements ApplicationContextAware {



    private final DistributedRateLimit distributedRateLimit;

    @Autowired
    public RedisLimitBootStrap(final DistributedRateLimit distributedRateLimit) {
        this.distributedRateLimit = distributedRateLimit;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.getInstance().setCfgContext((ConfigurableApplicationContext) applicationContext);
        start(RedisLimiteConfigList.getRedisLimiteConfigs());
    }

    /**
     * TODO 目前只是一个key测试 如果需要支持多个 需要每次初始化多个key
     * @param
     */
    private void start(List<RedisLimiteConfig> redisLimites) {
        for (RedisLimiteConfig redisLimiteConfig : redisLimites) {
            distributedRateLimit.init(redisLimiteConfig.getKey(),redisLimiteConfig.getContext(),
                    redisLimiteConfig.getMaxPermits(),redisLimiteConfig.getRate());
        }

    }
}
