package com.zl.reactor.FluxMethod.blockfirst;

import org.junit.Test;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Chapter1 {


    public static void main(String[] args){


        //testImmediateReturn();
        //testEmptyFlux();
        testBlockFiveSecond();

    }

    /**
     *
     * BLOCK拿到第一个与元素前阻塞
     */
    @Test
    private static void testImmediateReturn() {
        long startTime = System.currentTimeMillis();

        String string = Flux.just("orange", "blue")
                .map(color -> "color-->" + color)
                .log()
                .blockFirst();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }


    /**
     * 空序列
     */
    @Test
    public static void testEmptyFlux() {
        long startTime = System.currentTimeMillis();

        String string = Flux.empty()
                .map(color -> "color-->" + color)
                .log()
                .blockFirst();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }


    /**
     * 测试加入sleep 5s
     */
    @Test
    public static void testBlockFiveSecond() {
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
                .blockFirst();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }


    /**
     * 测试异常
     */
    @Test
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
                .blockFirst();

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(string);
    }


    /**
     *
     *
     * 由此可知 异步的 阻塞
     */
    @Test
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
                .blockFirst();

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
