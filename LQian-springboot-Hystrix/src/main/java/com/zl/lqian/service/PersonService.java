package com.zl.lqian.service;

import com.zl.lqian.entity.Result;

public interface PersonService {


    /**
     * 信号量方式
     * @param arg
     * @return
     */
    Result semaphore(String arg);

    /**
     * 线程方式
     * @param arg
     * @return
     */
    Result thread(String arg);

}
