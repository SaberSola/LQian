package com.lqian.repetition.lock;

public abstract class AbstractDistributedLock implements DistributedLock {


    @Override
    public boolean lock(String key) {
        return lock(key, lockvalue, TIMEOUT_MILLIS);
    }


    public boolean lock(String key,String  lockvalue,long TIMEOUT_MILLI){
        return false;
    }

}