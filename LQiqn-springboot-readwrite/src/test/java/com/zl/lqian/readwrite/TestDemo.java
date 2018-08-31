package com.zl.lqian.readwrite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo {

    private static final String url = "http://localhost:16830/getid";

    public static  void main(String[] args) throws InterruptedException{

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i =0 ; i< 20; i++){
           executorService.execute(()->{
               String result = HttpUtils.sendGet(url);
               System.out.println(result);
           });
        }

    }
}
