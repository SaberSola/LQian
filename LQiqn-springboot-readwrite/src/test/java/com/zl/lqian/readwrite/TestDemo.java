package com.zl.lqian.readwrite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo {

    private static final String url = "http://localhost:8088/api/v1/findByUserId?userId=10000";

    public static  void main(String[] args) throws InterruptedException{

        ExecutorService executorService = Executors.newFixedThreadPool(60);
        for (int i =0 ; i< 60; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    HttpUtils.sendGet(url);

                }
            });
            Thread.sleep(1000);
        }

    }
}
