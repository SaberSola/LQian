package com.zl.lqian.readwrite;

import com.zl.lqian.readwrite.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo {

    private static final String url = "http://localhost:8088/api/v1/findByUserId?userId=10000";

    public static  void main(String[] args) throws InterruptedException{

        /*ExecutorService executorService = Executors.newFixedThreadPool(60);
        for (int i =0 ; i< 5; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    HttpUtils.sendGet(url);

                }
            });
            Thread.sleep(1000);
        }
*/
        Order order ;
        List<Object> list = new ArrayList<>();
        /*for (;;){
            order = new Order();
            System.out.println(order);
            list.add(order);
        }*/

        List<Object> list1 = new ArrayList<>();
        for (; ;){
            Order order1 = new Order();
            System.out.println(order1);
            list1.add(order1);
        }

        //System.out.println(11);
    }
}
