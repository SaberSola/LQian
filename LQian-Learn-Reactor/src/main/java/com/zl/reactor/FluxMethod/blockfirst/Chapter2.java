package com.zl.reactor.FluxMethod.blockfirst;

import org.junit.Test;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Chapter2 {



    public static void main(String[] args) {
        testWithTimeout();
    }


    /**
     *
     * 这里给出了超时时限
     */
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
                .blockFirst(Duration.ofSeconds(1));

        System.out.println(string);
    }
}
