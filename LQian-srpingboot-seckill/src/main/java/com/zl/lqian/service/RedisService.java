package com.zl.lqian.service;

public interface RedisService {

    public void  set(String key,Object value,Long expireTime);

    public void  set(String key,Object value);

    public void delete(String key);

    public Object get(String key,Class clazz);

    public boolean exists(String key);

    public Long incr(String key);

    public Long decr(String key);

}
