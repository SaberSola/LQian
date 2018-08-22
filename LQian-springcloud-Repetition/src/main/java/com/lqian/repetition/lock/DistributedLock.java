package com.lqian.repetition.lock;


public interface DistributedLock {


    public static final long TIMEOUT_MILLIS = 30000;

    public static final String lockvalue = "keyexits";

    public static final long SLEEP_MILLIS = 500;

    public boolean lock(String key);


    public boolean releaseLock(String key);
}
