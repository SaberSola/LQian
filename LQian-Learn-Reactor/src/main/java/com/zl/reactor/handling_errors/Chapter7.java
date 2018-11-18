package com.zl.reactor.handling_errors;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

/**
 * Using Resources and the Finally Block
 * <p>
 * The last parallel to draw with imperative programming is the cleaning up that can be done either via a Java 7
 * "try-with-resources" construct or the use of the finally block. This is the equivalent of (use the finally block to
 * clean up resources or a Java 7 "try-with-resource" construct).
 * Both have their Reactor equivalents, using and doFinally
 * <p>
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/using.png
 * <p>
 * On the other hand, doFinally is about side-effects that you want to be executed whenever the sequence terminates,
 * whether with onComplete, onError, or cancellation. It gives you a hint as to what kind of termination triggered
 * the side-effect
 */
public class Chapter7 {

    public static void main(String[] args) {
//        testUsing();
        testDoFinally();
    }

    public static void testUsing() {
        AtomicBoolean isDisposed = new AtomicBoolean();
        Disposable disposableInstance = new Disposable() {

            @Override
            public void dispose() {
                System.out.println("dispose");
                // After subscription and execution of the sequence, the isDisposed atomic boolean would become true.
                isDisposed.set(true);
            }

            @Override
            public String toString() {
                return "DISPOSABLE";
            }
        };

        Flux<String> flux = Flux.using(
                // The first lambda generates the resource. Here we return our mock Disposable.
                () -> disposableInstance,
                // The second lambda processes the resource, returning a Flux<T>.
                disposable -> Flux.just(disposable.toString()),
                // The third lambda is called when the Flux from 2) terminates or is cancelled, to clean up resources.
                Disposable::dispose
        );

        flux.subscribe(System.out::println);
        System.out.println(isDisposed);
    }

    public static void testDoFinally() {
        // We assume we want to gather statistics. Here we use a LongAdder.
        LongAdder statsCancel = new LongAdder();

        Flux<String> flux = Flux.just("foo", "bar")
                .doFinally(type -> {
                    // doFinally consumes a SignalType for the type of termination.
                    if (type == SignalType.CANCEL) {
                        // Here we increment statistics in case of cancellation only.
                        statsCancel.increment();
                    }
                })
                .take(1); // take(1) will cancel after 1 item is emitted.

        flux.subscribe(System.out::println).dispose();
        System.out.println(statsCancel);
    }
}
