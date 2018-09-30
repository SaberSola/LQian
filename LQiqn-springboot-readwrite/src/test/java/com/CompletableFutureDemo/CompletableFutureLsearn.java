package com.CompletableFutureDemo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompletableFutureLsearn {

    @Test
    public  void  test1 () throws Exception{

       CompletableFuture future =  CompletableFuture.supplyAsync(() -> {
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
                Thread.sleep(3000);
                System.out.println("alqiansf");
                System.out.println(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " and and and " + s2));

       future.get();
    }

    @Test
    public  void test2() throws Exception{
       CompletableFuture future=  CompletableFuture.supplyAsync(()->{
            try {
             /*   String result = print();
                System.out.println(result);*/
                System.out.println("fasdfasdfsad");
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(()->{

            try {
               // String result = print1();
                //System.out.println(result);
                Thread.sleep(2000);
                System.out.println(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "s2";
        }),()-> System.out.println("hello world"));
       future.join();
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
                Thread.sleep(3000);
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
        CompletableFuture.completedFuture("thenAcceptmessage").thenAccept(s ->result.append(s));
        System.out.println(result.length());
    }

    /**
     * 上一步代码的异步消费方式
     */
    @Test
    public void thenAcceptAsyncExample(){
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void>cf = CompletableFuture.completedFuture("then")
                .thenAcceptAsync(s ->result.append(s));
        cf.join();
        System.out.println(result.length());
    }

    /**
     * 演示了CompletableFuture等待俩个阶段同步执行,第一个阶段执行大写转换，第二个阶段执行小写转换。
     */
    @Test
    public void runAfterBothExample(){
        String original = "Message";
        StringBuilder result  = new StringBuilder();
        CompletableFuture  sf  = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).
                runAfterBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),()->{
                    result.append("done");
                });

        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(result.length());
    }

    /**
     * 处理俩个过程的结果
     */
    @Test
    public void thenAcceptBothExample(){

        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).
                thenAcceptBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),(s1,s2)->{
                   result.append(s1+s2);
                });
        System.out.println(result.toString());
    }

    /**
     * 如果CompletableFuture依赖两个前面阶段的结果，
     * 它复合两个阶段的结果再返回一个结果，我们就可以使用thenCombine()函数。
     * 整个流水线是同步的，所以getNow()会得到最终的结果，它把大写和小写字符串连接起来。
     */
    @Test
    public void thenCombineExample(){
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),(s1,s2)-> s1+s2
                );
       /* try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        System.out.println(cf.getNow(null));
    }

    /**
     * 上一步的异步执行
     * 最后需要使用join方法等待结构
     */
    @Test
    public void thenCombineAsyncExample(){
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s->delayedLowerCase(s)),
                        (s1,s2)-> s1 + s2);
        //立即返回结果
        System.out.println(cf.getNow("abc"));
        System.out.println(cf.join());
    }

    private static String delayedUpperCase(String s) {
        delay();
        return s.toUpperCase();
    }
    private static String delayedLowerCase(String s) {
        delay();
        return s.toLowerCase();
    }

    public static void delay() {
        int delay = 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 这个方法等待第一个阶段的完成(大写转换)，
     * 它的结果传给一个指定的返回CompletableFuture函数，
     * 它的结果就是返回的CompletableFuture的结果。
     *
     */
    @Test
    public void thenComposeExample(){
        String original = "Message";
        CompletableFuture sf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
                        .thenApply(s -> upper + s));
        System.out.println(sf.getNow(null));
    }


    /**
     * 只要有一个阶段完成 就算是完成
     */
    @Test
    public void anyOfExample(){
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg->CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res,th)->{
            if (th == null){
                System.out.println((String) res);
                result.append(res);
            }
        });
    }
    /**
     * 这里必须要全部阶段完成
     */
    @Test
    public void allOfExample(){
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> System.out.println(cf.getNow(null)));
            result.append("done");
        });
    }

    /**
     * 上一步的异步执行
     */
    @Test
    public void allOfAsyncExample(){

        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> System.out.println(cf.getNow(null)));
                    result.append("done");
                }).toCompletableFuture().join();
    }

}
