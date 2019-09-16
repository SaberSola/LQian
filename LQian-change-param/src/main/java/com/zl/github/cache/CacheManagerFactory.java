package com.zl.github.cache;

import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static lombok.AccessLevel.PRIVATE;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@NoArgsConstructor(access = PRIVATE)
public class CacheManagerFactory {

    private static final Map<String, Map<String, Object>> CACHE_MAP = new ConcurrentHashMap<>();


    public static CacheManager getCacheManager(final String cacheName) {
        if (Objects.isNull(cacheName)){
            throw new RuntimeException("cacheName can not be null");
        }
        return new CacheManager(CACHE_MAP.computeIfAbsent(cacheName, k -> new ConcurrentHashMap<>()));
    }
}
