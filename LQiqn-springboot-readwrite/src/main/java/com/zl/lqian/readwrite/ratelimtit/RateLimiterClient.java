package com.zl.lqian.readwrite.ratelimtit;


import com.google.common.collect.ImmutableList;
import com.zl.lqian.readwrite.common.constants.RateLimterConstants;
import com.zl.lqian.readwrite.common.type.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

/**
 * @Author
 * redis限流操作类
 * TODO 1 要有初始化的操作
 * TODO 2 要有取令牌的操作
 *
 */
@Service
public class RateLimiterClient implements DistributedRateLimit{


    private Logger logger = LoggerFactory.getLogger(RateLimiterClient.class);

    private StringRedisTemplate stringRedisTemplate;

    private RedisScript<Long> rateLimiterLua;

    @Autowired
    public RateLimiterClient(StringRedisTemplate stringRedisTemplate, RedisScript<Long> rateLimiterLua ){

        this.stringRedisTemplate = stringRedisTemplate;
        this.rateLimiterLua = rateLimiterLua;
    }

    /**
     * TODO 需要写个初始化lua 脚本
     * @param key
     * @param context
     * @param maxPermits
     */

    @Override
    public void init(String key, String context, Integer maxPermits,Integer rate) {
        stringRedisTemplate.execute(rateLimiterLua,
                ImmutableList.of(getKey(key)),
                        RateLimterConstants.RATE_LIMITER_INIT_METHOD, maxPermits + "", rate + "", context);
    }


    /**
     *
     * 获取token  默认取一个令牌
     * @param context
     * @param key
     * @return
     *
     */
    @Override
    public boolean acquire(String context, String key) {
        Token token = acquireToken(context, key);
        return token.isPass();
    }


    private Token acquireToken(String context, String key) {

        return acquireToken(context, key, 1);
    }


    public Token acquireToken(String context, String key, Integer permits) {
        Token token;
        try {

            //返回当前时间redis服务器的时间
            //TODO 妈的 自己环境可以用表达式 公司的工程环境不行
        /*    RedisCallback<Long> redisCallback = redisConnection ->{ return redisConnection.time(); };

            Long currMillSecond = stringRedisTemplate.execute(redisCallback);*/
            //开始申请令牌
            Long currMillSecond = stringRedisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.time();
                }
            });
            Long acquire = stringRedisTemplate.execute(rateLimiterLua, ImmutableList.of(getKey(key)),
                    RateLimterConstants.RATE_LIMITER_ACQUIRE_METHOD, permits.toString(), currMillSecond.toString(), context);
            if (acquire == 1) {
                token = Token.PASS;
            } else if (acquire == -1) {
                token = Token.FUSING;
            } else {
                logger.error("no rate limit config for context={}", context);
                token = Token.NO_CONFIG;
            }

        }catch (Throwable e){
            logger.error("get rate limit token from redis error,key=" + key, e);
            token = Token.ACCESS_REDIS_FAIL;
        }
        return token;

    }


    /**
     * 获取redis中存储的key
     * @param key
     * @return
     */
    private String getKey(String key) {
        return RateLimterConstants.RATE_LIMITER_KEY_PREFIX + key;
    }

}
