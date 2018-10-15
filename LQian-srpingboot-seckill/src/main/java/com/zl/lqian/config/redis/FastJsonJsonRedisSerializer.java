package com.zl.lqian.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.Charset;

public class FastJsonJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    static {
        ParserConfig.getGlobalInstance().addAccept("com.zl.lqian");
    }

    public FastJsonJsonRedisSerializer() {
    }

    public FastJsonJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        String str = new String(bytes, DEFAULT_CHARSET);
        if (clazz == null) {
            return (T) JSON.parse(str);
        }
        return (T) JSON.parseObject(str, clazz);
    }

}