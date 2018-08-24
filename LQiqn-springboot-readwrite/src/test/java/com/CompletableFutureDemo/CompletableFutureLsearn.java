package com.CompletableFutureDemo;

import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompletableFutureLsearn {

    public static void  test1 () throws Exception{

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("zhangleicesji");
                System.out.println(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("alqiansf");
                System.out.println(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " and and and " + s2));
    }

    public static void test2() throws Exception{
        CompletableFuture.supplyAsync(()->{
            try {
             /*   String result = print();
                System.out.println(result);*/
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(()->{

            try {
               // String result = print1();
                //System.out.println(result);
                System.out.println(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "s2";
        }),()-> System.out.println("hello world"));
    }
    @Test
    public void completedFutureExample(){
        CompletableFuture<String>cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow("message"));
    }
    @Test
    public void runAsyncExample() {
        CompletableFuture<Void>cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        System.out.println("查看状态"+cf.isDone());
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("再次查看状态"+cf.isDone());
    }
    @Test
    public void thenApplyExample(){
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            System.out.println("守护线程是否："+Thread.currentThread().isDaemon());
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
       /* try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        System.out.println(cf.getNow(null));
    }
    @Test
    public void  thenApplyAsyncExample() {
        CompletableFuture<String>cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println("守护线程是否："+Thread.currentThread().isDaemon());
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        System.out.println("调用join方法之前:"+cf.getNow(null));
        System.out.println( cf.join());
    }


    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "custom-executor-" + count++);
        }
    });
    @Test
    public void thenApplyAsyncExampleExcutors(){
        CompletableFuture<String>cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println("线程池："+Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executor);
        System.out.println("没用json之前:"+cf.getNow(null));
        System.out.println(cf.join());
    }

    /**
     *
     * 本次计算只需要前一次的计算结果，
     * 而不需要返回本次计算结果
     * 那就有点类似于生产者（前一次计算）-消费者（本次计算）模式了
     */
    @Test
    public void main(){
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message").thenAccept(s ->result.append(s));
        System.out.println(result.length());
    }

    /**
     * 上一步代码的异步消费方式
     */
    @Test
    public void thenAcceptAsyncExample(){
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void>cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s ->result.append(s));
        cf.join();
        System.out.println(result.length());
    }
}
