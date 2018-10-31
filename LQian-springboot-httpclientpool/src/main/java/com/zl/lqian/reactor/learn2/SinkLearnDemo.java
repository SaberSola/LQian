package com.zl.lqian.reactor.learn2;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SinkLearnDemo {


    @Test
    public void testOne(){


        /**
         *   最简单的创建flux的方法generate 方法
         *   这是一种 同步地， 逐个地 产生值的方法，
         *   意味着 sink 是一个 SynchronousSink 而且其 next() 方法在每次回调的时候最多只能被调用一次。
         *   你也可以调用 error(Throwable) 或者 complete()，不过是可选的。
         *
         *   最有用的一种方式就是同时能够记录一个状态值（state），
         *   从而在使用 sink 发出下一个元素的时候能够 基于这个状态值去产生元素。
         *   此时生成器（generator）方法就是一个 BiFunction<S, SynchronousSink<T>, S>， 其中 <S> 是状态对象的类型
         *   你需要提供一个 Supplier<S> 来初始化状态值，而生成器需要 在每一“回合”生成元素后返回新的状态值（供下一回合使用）。
         */
        Flux<String> flux = Flux.generate(()-> 0,                     //初始化state 的值为0
                (state,sink)->{                                       //sink = new SynchronousSink；
                    sink.next("3 x " + state + " = " + 3*state);   //我们基于状态值 state 来生成下一个值（state 乘以 3) x
                    if (state == 10) sink.complete();                 //complete终止序列
                    return state + 1;                                 //返回值作为下下一次调用
                });
        flux.subscribe();
        System.out.println("process  generate int ----------------> limit -----------> line");

        Flux<String> fluxLong = Flux.generate(
                AtomicLong::new,                                     //声明一个初始值
                (state, sink) -> {
                    long i = state.getAndIncrement();                //改变状态量
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) sink.complete();                    //终止序列
                    return state;
                }, (state) -> System.out.println("state: " + state)); //最后一个状态值（11）会被这个 Consumer lambda 输出。

        fluxLong.subscribe();
        System.out.println("process  generate fluxLong ----------------> limit -----------> line");
        /**
         *  create 方法的生成方式既可以是同步，
         *  也可以是异步的，并且还可以每次发出多个元素。
         */
           MyEventProcessor myEventProcessor = new MyEventProcessor() {

            private MyEventListener<String> eventListener;
            private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

            private SingleThreadEventListener singleThreadEventListener;
            @Override
            public void register(MyEventListener<String> eventListener) {
                this.eventListener = eventListener;
            }

           public void register(SingleThreadEventListener<String> eventListener) {
                   this.singleThreadEventListener = eventListener;
            }

            @Override
            public void dataChunk(String... values) {
                executor.schedule(() -> eventListener.onDataChunk(Arrays.asList(values)),
                        500, TimeUnit.MILLISECONDS);
            }

            @Override
            public void processComplete() {
                executor.schedule(() -> eventListener.processComplete(),
                        500, TimeUnit.MILLISECONDS);
            }
        };
         Flux<String> bridge  = Flux.create(sink -> {
             myEventProcessor.register(new MyEventListener<String>() {
                 @Override
                 public void onDataChunk(List<String> chunk) {
                     for(String s : chunk) {
                         sink.next(s);
                     }
                 }
                 @Override
                 public void processComplete() {
                     sink.complete();
                 }
             });
         });
        bridge.subscribe();
        /**
         * push 模式
         */
        Flux<String> bridgePush = Flux.push(sink -> {
            myEventProcessor.register(
                    new SingleThreadEventListener<String>() {

                        public void onDataChunk(List<String> chunk) {
                            for(String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }

                        public void processError(Throwable e) {
                            sink.error(e);
                        }
                    });
        });
        bridgePush.subscribe();
        /**
         * pull/push 混合模式
         */
        MyMessageProcessor myMessageProcessor = new MyMessageProcessor();
        Flux<String> briagePullPush = Flux.create(sink -> {
            myMessageProcessor.register(new MyMessageListener<String>(){
                @Override
                public void onMessage(List<String> messages) {
                    for(String s : messages) {
                        sink.next(s);
                    }
                }
            });
            sink.onRequest(n ->{
                List<String> messages = myMessageProcessor.request(3);
                for(String s : messages) {
                    sink.next(s);
                }
            });

        });
        briagePullPush.subscribe(System.out::println);

    }
    interface MyMessageListener<T> {

        void onMessage(List<T> messages);
    }

    static class MyMessageProcessor {

        void register(MyMessageListener<String> listener) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> chunk = Arrays.asList("name", "age", "sex", "date");
            listener.onMessage(chunk);
        }

        List<String> request(long request) {
            return LongStream.range(1, 1 + request).mapToObj(number -> "number-->" + number).collect(Collectors.toList());
        }
    }
}
