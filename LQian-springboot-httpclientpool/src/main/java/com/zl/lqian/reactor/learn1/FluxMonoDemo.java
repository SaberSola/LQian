package com.zl.lqian.reactor.learn1;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FluxMonoDemo {



    @Test
    public void testOne(){


        /**
         *  flux 是1 - n 的序列
         *  mono 是异步 0 - 1 的结果
         */
        //创建一个string序列
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        //工厂方式创建mono
        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");

        //range 从5 开始生成三个值
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);

        /**
         * subscribe();                                                     订阅并触发序列
         *
         * subscribe(Consumer<? super T> consumer);                         对产生的每一个序列进行消费
         *
         * subscribe(Consumer<? super T> consumer,
         *           Consumer<? super Throwable> errorConsumer);            对正常的元素进行消费,同时对错误进行响应
         *
         * subscribe(Consumer<? super T> consumer,
         *           Consumer<? super Throwable> errorConsumer,
         *           Runnable completeConsumer);                            对正常元素和错误均有响应，还定义了序列正常完成后的回调。
         *
         * subscribe(Consumer<? super T> consumer,
         *           Consumer<? super Throwable> errorConsumer,
         *           Runnable completeConsumer,
         *           Consumer<? super Subscription> subscriptionConsumer);  对正常元素、错误和完成信号均有响应， 同时也定义了对该 subscribe 方法返回的 Subscription 执行的回调。
         *
         *           以上方法会返回一个 Subscription 的引用，如果不再需要更多元素你可以通过它来取消订阅。
         *           取消订阅时， 源头会停止生成新的数据，并清理相关资源。取消和清理的操作在 Reactor 中是在 接口 Disposable 中定义的
         */
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(); // 最简单的订阅，订阅并触发序列

        //ints.subscribe(i -> System.out.println(i));
        //更形象的体现 写成内部类的形式
        ints.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        }); // 这里会输出3 个值
        System.out.println("process --------> limit  -------------> line");
        Flux<Integer> intserror = Flux.range(1,4)   //map 转换函数
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        intserror.subscribe(integer -> System.out.println(integer),
                            error ->   System.err.println("Error: " + error));

        System.out.println("process error --------> limit  -------------> line");

        Flux<Integer> intsDone = Flux.range(1, 4);

        intsDone.subscribe(i -> {try {
            Thread.sleep(2000);
                }catch (InterruptedException e){
            e.printStackTrace();
                }System.out.println(i);},                     //处理正常的数据
                           error -> System.err.println("Error " + error),  //处理异常
                           () -> {System.out.println("Done");              //完成后的处理
        });
        System.out.println("process done --------> limit  -------------> line");

        //自定义的subscriber
        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> intsSub = Flux.range(1, 4);
        intsSub.subscribe(i -> System.out.println(i),                     //正常处理数据
                       error -> System.err.println("Error " + error),  //异常处理
                       () -> {System.out.println("Done");},            //完成后处理
                       s -> ss.request(10));                        //subscribe 方法返回的 Subscription 执行的回调
        ints.subscribe(ss);
        System.out.println("process subscribe --------> limit  -------------> line");

        Flux<String> source = Flux.just("foo", "bar", "foobar");
        source.map(String::toUpperCase)
                .subscribe(new BaseSubscriber<String>() {    //抽象类 只能创建匿名内部类
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        //BaseSubscriber 定义了处理不同信号的钩子
                        request(1);   //这里实现了背压原理 在任何的hook(钩子函数中)像上游传递请求，这里像上游请求1个元素
                    }

                    @Override
                    protected void hookOnNext(String value) {
                        request(1); //随着接收到新的值 这里继续像上游请求
                    }
                });
    }
}
