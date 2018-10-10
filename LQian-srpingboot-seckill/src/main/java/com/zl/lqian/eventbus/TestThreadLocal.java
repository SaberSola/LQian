package com.zl.lqian.eventbus;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal {

    public static void  main(String[]  args){

        /**
         * 这里证明了ThreadLocal 是由线程决定的 ThreadLocal是绑定线程的
         */
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i= 0 ; i < 10 ; i++ ) {
            executorService.execute(() -> {
                Random random = new Random();
                Integer res = random.nextInt();
                System.out.println(res);
                ConcurrentThreadLocal.getInstance().set("zl + " +res);

                String result = ConcurrentThreadLocal.getInstance().get();
                System.out.println(result);

                //一定要清楚 否则可能引起内存泄漏
                ConcurrentThreadLocal.getInstance().remove();
            });
        }
    }
}
