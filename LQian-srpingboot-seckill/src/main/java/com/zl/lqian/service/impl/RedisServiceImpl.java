package com.zl.lqian.service.impl;

import com.alibaba.fastjson.JSON;
import com.zl.lqian.service.RedisService;
import com.zl.lqian.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCommands;


/**
 * 需要测试connection是否需要close
 * 源码中可以看到excute（）中finaly 会close connection
 */
@Service
public class RedisServiceImpl implements RedisService {


    private final RedisTemplate redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    public RedisServiceImpl(RedisTemplate redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(final String key, final Object value, final Long expireTime) {
        this.redisSet(key, value, expireTime);
    }

    @Override
    public void set(final String key, final Object value) {
        this.redisSet(key, value, null);
    }

    /**
     * todo 这种方法线程并不安全
     * @param key
     * @param value
     * @param expireTime
     */
    public void redisSet(final String key, final Object value, final Long expireTime) {

        if (StringUtils.isEmpty(key)) {
            LogUtil.info(LOGGER, () -> "this kill could not be null");
            return;
        }
        String result = beanToString(value);
        RedisCallback callback = connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            commands.set(key, result);
            if (null != expireTime && expireTime > 0) {
                commands.pexpire(key, expireTime);
            }
            return 1l;
        };
        redisTemplate.execute(callback);
    }

    @Override
    public void delete(final String key) {

        if (StringUtils.isEmpty(key)) {
            LogUtil.info(LOGGER, () -> "this kill could not be null");
            return;
        }
        RedisCallback callback = connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            Long reult = commands.del(key);
            return reult;
        };

        redisTemplate.execute(callback);
    }

    @Override
    public Object get(final String key, final Class clazz) {
        if (StringUtils.isEmpty(key)) {
            LogUtil.info(LOGGER, () -> "this kill could not be null");
            return null;
        }
        RedisCallback<String> callback = connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            return commands.get(key);
        };
        String result = (String) redisTemplate.execute(callback);
        if (!StringUtils.isEmpty(result)) {
            return stringToBean(result, clazz);
        }
        return null;
    }

    @Override
    public boolean exists(String key) {
        if (StringUtils.isEmpty(key)) {
            LogUtil.info(LOGGER, () -> "this kill could not be null");
            return false;
        }
        RedisCallback<Boolean> callback = connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            return commands.exists(key);
        };
        Boolean result = (Boolean)redisTemplate.execute(callback);
        return result;
    }

    @Override
    public Long incr(String key) {
        if (StringUtils.isEmpty(key)) {
            LogUtil.info(LOGGER, () -> "this kill could not be null");
            return null;
        }
        RedisCallback<Long> callback = connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            return commands.incr(key);
        };
        Long result = (Long) redisTemplate.execute(callback);
        return result;
    }

    @Override
    public Long decr(String key) {
        if (StringUtils.isEmpty(key)) {
            LogUtil.info(LOGGER, () -> "this kill could not be null");
            return null;
        }
        RedisCallback<Long> callback = connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            return commands.decr(key);
        };
        Long result = (Long) redisTemplate.execute(callback);
        return result;
    }


    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return String.valueOf(value);
        }
        else if (clazz == long.class || clazz == Long.class) {
            return String.valueOf(value);
        }
        else if (clazz == String.class) {
            return (String) value;
        }
        else {
            return JSON.toJSONString(value);
        }

    }

    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        }
        else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        }
        else if (clazz == String.class) {
            return (T) str;
        }
        else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }
}
