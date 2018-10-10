package com.zl.lqian.eventbus;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class GoogleCache {


    private static final int MAX_COUNT = 10000;

    /**
     * caheLoader是一种自动加载 l当getkey不存在的时候 会根据load中的方法作为key 的vaule
     */
    private static final LoadingCache<String, String> LOADING_CACHE =
            CacheBuilder.newBuilder().maximumWeight(MAX_COUNT)
                    .weigher((Weigher<String, String>) (string, tccTransaction) -> getSize())
                    .build(new CacheLoader<String, String>() {
                        @Override
                        public String load(final String key) {
                            return cacheTccTransaction(key);
                        }
                    });

    private static int getSize() {
        return (int) LOADING_CACHE.size();
    }

    private static String cacheTccTransaction(final String key) {
        return "zl";
    }

    public void put(final String key,String value) {
        LOADING_CACHE.put(key, value);
    }

    public String get(final String key) {
        try {
            return LOADING_CACHE.get(key);
        } catch (ExecutionException e) {
            return "zl";
        }
    }

    public void removeByKey(final String key) {
            LOADING_CACHE.invalidate(key);
    }

    public static void main(String[] args){

        GoogleCache googleCache = new GoogleCache();
      /*  googleCache.put("a","bfs");
        googleCache.put("b","bf");
        googleCache.put("g","bsd");
        googleCache.put("c","saf");
        System.out.println(googleCache.get("a"));
        System.out.println(googleCache.get("b"));
        System.out.println(googleCache.get("g"));
        System.out.println(googleCache.get("v"));*/

        System.out.println(googleCache.get("撒旦法发发"));


    }
}
