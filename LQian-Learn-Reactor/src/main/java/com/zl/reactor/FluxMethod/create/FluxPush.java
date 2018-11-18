package com.zl.reactor.FluxMethod.create;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class FluxPush {


    /**
     *
     * create 的一个变体是 push，适合生成事件流。与 create`类似，`push 也可以是异步地，
     * 并且能够使用以上各种溢出策略（overflow strategies）管理背压。每次只有一个生成线程可以调用 next，complete 或 error。
     * @param args
     */
    public static void main(String[] args){


        MyEventProcessor myEventProcessor = new MyEventProcessor();

        Flux<String> bridge = Flux.push(sink->{

            myEventProcessor.register(new SingleThreadEventListener<String>() {
                @Override
                public void onDataChunk(List<String> chunk) {
                    for (String s : chunk) {
                        // Events are pushed to the sink using next from a single listener thread.
                        sink.next(s);
                    }
                }

                @Override
                public void processComplete() {
                    System.out.println("The processComplete event is translated to onComplete.");
                    // complete event generated from the same listener thread.
                    sink.complete();
                }

                @Override
                public void processError(Throwable e) {
                    System.out.println("The processError event is translated to onError.");
                    // error event also generated from the same listener thread.
                    sink.error(e);
                }
            });

        });

        bridge.subscribe(System.out::println, Throwable::printStackTrace);


    }


    interface SingleThreadEventListener<T> {

        void onDataChunk(List<T> chunk);

        void processComplete();

        void processError(Throwable e);
    }

   static class MyEventProcessor {

    public void register(SingleThreadEventListener<String> eventListener) {
        List<String> chunk = Arrays.asList("name", "age", "sex", "date");
        eventListener.onDataChunk(chunk);
        eventListener.processComplete();
        eventListener.processError(new RuntimeException("error"));
       }
    }
}
