package com.zl.reactor.chapter;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class ChapterMonoFlux {




    @Test
    public void chapter1(){

        /**
         *工厂方式产生一个Flux
         */
        Flux<Integer> ints = Flux.range(1,3);

        /**
         * 开始订阅
         */
        ints.subscribe();

    }

    @Test
    public void chapter2(){

        Flux<Integer> ints = Flux.range(1,3);

        /**
         * 开始订阅 带有消费者的
         */
        ints.subscribe(integer ->System.out.println(integer));


    }

    /**
     * 抛异常会结束序列
     */
    @Test
    public void chapter3(){

        Flux<Integer> ints = Flux.range(1,5)
                .map(integer -> {
                    if(integer <= 3){
                        return integer;
                    }
                    throw new RuntimeException("GOt to 4");
                });

        /**
         *
         * 开始订阅 并且抛出异常
         *
         */
        ints.subscribe(integer -> {System.out.println(integer);},error->{

           System.out.println("ERROR：" + error);
        });

    }

    @Test
    public void chapter4(){

        // Set up a Flux that produces four values when a Subscriber attaches
        Flux<Integer> ints = Flux.range(1, 4);

        // Subscribe with a Subscriber that includes a handler for completion events
        ints.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));
    }

    @Test
    public void chapter5(){

        //声明一个订阅者
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        //声明序列
        Flux<Integer> ints = Flux.range(1,4);

        //订阅
        ints.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                s -> ss.request(10));

        //指明订阅者
        ints.subscribe(ss);

    }

    /**
     * 背压
     * 像生产者去请求
     */
    @Test
    public void chapter6(){

        Flux<String> source = Flux.fromStream(Stream.of("name","age","sex","date"));
        /**
         * 	BaseSubscriber 是一个抽象类，所以我们创建一个匿名内部类。
         *
         *  BaseSubscriber 定义了多种用于处理不同信号的 hook。它还定义了一些捕获 Subscription 对象的现成方法，这些方法可以用在 hook 中。
         *
         * 	request(n) 就是这样一个方法。它能够在任何 hook 中，通过 subscription 向上游传递 背压请求。这里我们在开始这个流的时候请求1个元素值。
         *
         *  随着接收到新的值，我们继续以每次请求一个元素的节奏从源头请求值。
         *
         *  其他 hooks 有 hookOnComplete, hookOnError, hookOnCancel, and hookFinally （它会在流终止的时候被调用，传入一个 SignalType 作为参数）。
         */
        source.map(String::toUpperCase)
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                      request(1);
                    }

                    @Override
                    protected void hookOnNext(String value) {
                        request(1);
                    }
                });


    }

    @Test
    public void chapter7(){


    }

    /**
     *
     * SampleSubscriber 类继承自 BaseSubscriber，在 Reactor 中, 推荐用户扩展它来实现自定义的 Subscriber。
     * 这个类提供了一些 hook 方法，我们可以通过重写它们来调整 subscriber 的行为。
     * 默认情况下，它会触发一个无限个数的请求，但是当你想自定义请求元素的个数的时候，扩展 BaseSubscriber 就很方便了。
     * 扩展的时候通常至少要覆盖 hookOnSubscribe(Subscription subscription) 和 hookOnNext(T value) 这两个方法。
     * 这个例子中， hookOnSubscribe 方法打印一段话到标准输出，然后进行第一次请求。 然后 hookOnNext 同样进行了打印，同时逐个处理剩余请求。
     * @param <T>
     */

    static class SampleSubscriber<T> extends BaseSubscriber<T>{


        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("SubScribed");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }
}
