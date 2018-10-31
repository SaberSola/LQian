package com.zl.lqian.reactor.chapter;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

public class FluxOnDispose {

    public static void main(String[] args){

        //testUsing();
        testDoFinally();
    }


    public static void testUsing() {
        AtomicBoolean isDisposed = new AtomicBoolean();

        Disposable disposableInstance = new Disposable() {
            @Override
            public void dispose() {
                System.out.println("dispose");
                // After subscription and execution of the sequence, the isDisposed atomic boolean would become true.
                isDisposed.set(true);           //	在订阅或执行流序列之后， isDisposed 会置为 true。
            }

            @Override
            public String toString() {
                return "DISPOSABLE";
            }
        };

        Flux<String> flux = Flux.using(
                // The first lambda generates the resource. Here we return our mock Disposable.
                () -> disposableInstance,                                                               	// 第一个 lambda 生成资源，这里我们返回模拟的（mock） Disposable
                // The second lambda processes the resource, returning a Flux<T>.
                disposable -> Flux.just(disposable.toString()),                                             // 第二个 lambda 处理资源，返回一个 Flux<T>。
                // The third lambda is called when the Flux from 2) terminates or is cancelled, to clean up resources.
                Disposable::dispose                                                                         // 第三个 lambda 在 2) 中的资源 Flux 终止或取消的时候，用于清理资源
        );

        flux.subscribe(System.out::println);
        System.out.println(isDisposed);

    }

    public static void testDoFinally() {
        // We assume we want to gather statistics. Here we use a LongAdder.
        LongAdder statsCancel = new LongAdder();             //我们想进行统计，所以用到了 LongAdder。

        Flux<String> flux = Flux.just("foo", "bar")
                .doFinally(type -> {
                    // doFinally consumes a SignalType for the type of termination.
                    if (type == SignalType.CANCEL) {        //doFinally 用 SignalType 检查了终止信号的类型。
                        // Here we increment statistics in case of cancellation only.
                        statsCancel.increment();            //如果只是取消 则需要自增
                    }
                })
                .take(1); // take(1) will cancel after 1 item is emitted.   能够在发出 1 个元素后取消流。

        flux.subscribe(System.out::println).dispose();
        System.out.println(statsCancel);
    }
}
