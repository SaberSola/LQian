package com.zl.reactor.FluxMethod.blocklast;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * public final T blockLast():
 * <p>
 * Subscribe to this Flux and block indefinitely until the upstream signals its last value or completes.
 * Returns that value, or null if the Flux completes empty. In case the Flux errors, the original exception is thrown
 * (wrapped in a RuntimeException if it was a checked exception).
 * <p>
 * Note that each blockLast() will trigger a new subscription: in other words, the result might miss signal from hot publishers.
 */

/**
 * 阻塞获取最后一个序列
 */
public class Chapter1 {

    public static void main(String[] args) {
        //testImmediateReturn();
        //testEmptyFlux();
        //testPerItemBlockFiveSecond();
        testThrowRuntimeException();
        //testWithCheckedException();
    }

    private static void testImmediateReturn() {
        long startTime = System.currentTimeMillis();

        String string = Flux.just("orange", "blue")
                .map(color -> "color-->" + color)
                .log()
                .blockLast();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }

    private static void testEmptyFlux() {
        long startTime = System.currentTimeMillis();

        String string = Flux.empty()
                .map(color -> "color-->" + color)
                .log()
                .blockLast();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }

    private static void testPerItemBlockFiveSecond() {
        long startTime = System.currentTimeMillis();

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
                .blockLast();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }

    private static void testThrowRuntimeException() {
        long startTime = System.currentTimeMillis();

        String string = Flux.just("orange", "blue")
                .map(color -> {
                    if (color.length() < 4) {
                        return "color-->" + color;
                    }
                    throw new RuntimeException("There's exception!");
                })
                .log()
                .blockLast();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }

    private static void testWithCheckedException() {
        long startTime = System.currentTimeMillis();

        String string = Flux.just("orange", "blue")
                .map(color -> {
                    try {
                        return mapWithCheckedException(color);
                    } catch (IOException e) {
                        throw Exceptions.propagate(e);
                    }
                })
                .log()
                .blockLast();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }

    private static String mapWithCheckedException(String color) throws IOException {
        if (color.length() < 4) {
            return "color-->" + color;
        }
        throw new IOException("There's io exception!");
    }
}
