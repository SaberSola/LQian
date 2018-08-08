package com.zl.lqian.readwrite.common;

import java.util.ArrayList;
import java.util.List;

public class RedisLimiteConfigList {


    private static List<RedisLimiteConfig> redisLimiteConfigs;

    static {

        redisLimiteConfigs = new ArrayList<>();
        RedisLimiteConfig redisLimiteConfig = new RedisLimiteConfig();
        redisLimiteConfig.setContext("zl");
        redisLimiteConfig.setKey("zhanglei");
        redisLimiteConfig.setMaxPermits(5);
        redisLimiteConfig.setRate(2);
        redisLimiteConfigs.add(redisLimiteConfig);
    }


    private RedisLimiteConfigList(){

    }

   /* *//**
     * TODO 这样的单例是不合适的
     * @return
     *//*
    public static RedisLimiteConfigList getInstance(){

        if (redisLimiteConfigs == null){
            return  new RedisLimiteConfigList();
        }
        return null;
    }
*/
    public static List<RedisLimiteConfig> getRedisLimiteConfigs() {
        return redisLimiteConfigs;
    }

}
