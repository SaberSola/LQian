package com.zl.reactor.FluxMethod.create;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Chapter1 {

    /**
     *
     * 作为一个更高级的创建 Flux 的方式， create 方法的生成方式既可以是同步， 也可以是异步的，并且还可以每次发出多个元素。
     *
     * create 有个好处就是可以将现有的 API 转为响应式，比如监听器的异步方法。
     *
     * 该方法用到了 FluxSink，后者同样提供 next，error 和 complete 等方法。 与 generate 不同的是，create 不需要状态值，另一方面，它可以在回调中触发 多个事件（即使是在未来的某个时间）。
     * @param args
     */
    public static void main(String[] args){

        MyEventProcessor myEventProcessor = new MyEventProcessor();

        Flux<String> birdge = Flux.create(sink->{   //FluxSInk 提供 next error complete 方法等结束序列
            myEventProcessor.register(new MyEventListener<String>() {
                @Override
                public void onDataChunk(List<String> chunk) {
                    System.out.println("Each element in a chunk becomes an element in the Flux.");
                    for (String s : chunk ){
                        sink.next(s);
                    }
                }

                @Override
                public void processComplete() {
                    System.out.println("The processComplete event is translated to onComplete.");
                    sink.complete();
                }
            });

        });

        birdge.subscribe(s -> System.out.println(s));

    }

    interface MyEventListener<T> {

        void onDataChunk(List<T> chunk);

        void processComplete();
    }

    static class MyEventProcessor {

        public void register(MyEventListener<String> eventListener) {
            List<String> chunk = Arrays.asList("name", "age", "sex", "date");
            eventListener.onDataChunk(chunk);
            eventListener.processComplete();
        }
    }
}
