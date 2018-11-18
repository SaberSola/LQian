package com.zl.reactor.FluxMethod.blocklast;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * public final T blockLast(Duration timeout):
 * <p>
 * Subscribe to this Flux and block until the upstream signals its last value, completes or a timeout expires.
 * Returns that value, or null if the Flux completes empty. In case the Flux errors, the original exception is thrown
 * (wrapped in a RuntimeException if it was a checked exception).
 * <p>
 * If the provided timeout expires,a RuntimeException is thrown.
 * <p>
 * Note that each blockLast() will trigger a new subscription: in other words, the result might miss signal from hot publishers.
 */
public class Chapter2 {

    public static void main(String[] args) {
        testWithTimeout();
    }

    private static void testWithTimeout() {
        String string = Flux.just("orange", "blue")
                .map(color -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw Exceptions.propagate(e);
                    }
                    return "color-->" + color;
                })
                .log()
                .blockLast(Duration.ofSeconds(1));

        System.out.println(string);
    }
}
