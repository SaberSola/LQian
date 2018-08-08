package com.zl.lqian.readwrite.common;


public class RedisLimiteConfig {

    private String key;
    private String context;
    private Integer maxPermits;
    private Integer rate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getMaxPermits() {
        return maxPermits;
    }

    public void setMaxPermits(Integer maxPermits) {
        this.maxPermits = maxPermits;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
