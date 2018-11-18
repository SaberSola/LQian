package com.zl.reactor.FluxMethod.create;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Chapter2 {


    /**
     *
     * 不像 push，create 可以用于 push 或 pull 模式，因此适合桥接监听器的 的 API，因为事件消息会随时异步地到来。
     *
     * 回调方法 onRequest 可以被注册到 FluxSink 以便跟踪请求。这个回调可以被用于从源头请求更多数据，或者通过在下游请求到来 的时候传递数据给 sink 以实现背压管理。
     *
     * 这是一种推送/拉取混合的模式， 因为下游可以从上游拉取已经就绪的数据，上游也可以在数据就绪的时候将其推送到下游。
     *
     * @param args
     */
    public static void main(String[] args) {
        MyMessageProcessor myMessageProcessor = new MyMessageProcessor();

        Flux<String> bridge = Flux.create(sink -> {
            myMessageProcessor.register(messages -> {
                for (String s : messages) {
                    // The remaining messages that arrive asynchronously later are also delivered.
                    sink.next(s);
                }
            });

            sink.onRequest(n -> {
                // Poll for messages when requests are made.
                List<String> messages = myMessageProcessor.request(3);
                for (String s : messages) {
                    // If messages are available immediately, push them to the sink.
                    sink.next(s);
                }
            });
        });

        bridge.subscribe(System.out::println);
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
