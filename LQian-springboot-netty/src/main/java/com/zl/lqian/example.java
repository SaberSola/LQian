package com.zl.lqian;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class example {

    public static void  main(String [] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {

                Thread.sleep(10000);
                return 10;
            }
        });

        int a = 10;
        System.out.println(a);
        int result =  a + future.get();

        System.out.println(result);

    }
}
