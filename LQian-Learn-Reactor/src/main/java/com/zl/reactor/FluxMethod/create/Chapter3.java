package com.zl.reactor.FluxMethod.create;


import reactor.core.publisher.Flux;

/**
 *
 * onDispose 和 onCancel 这两个回调用于在被取消和终止后进行清理工作。
 * onDispose 可用于在 Flux 完成，有错误出现或被取消的时候执行清理。
 * onCancel 只用于针对“取消”信号执行相关操作，会先于 onDispose 执行。
 */
public class Chapter3 {

    public static void main(String[] args) {
        Channel channel = new Channel();

        Flux<String> bridge = Flux.create(sink -> sink.onRequest(channel::poll)
                // onCancel is invoked for cancel signal.
                .onCancel(channel::cancel)
                // onDispose is invoked for complete, error, or cancel.
                .onDispose(channel::close));

        bridge.subscribe(System.out::println).dispose();
    }

    static class Channel {

        void poll(long n) {
            System.out.println("request count-->" + n);
        }

        void cancel() {
            System.out.println("cancel");
        }

        void close() {
            System.out.println("close");
        }
    }


}
