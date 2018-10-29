package com.zl.lqian.reactor.chapter;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 演示终止方法 onError
 */
public class FluxOnError {

    public static void main(String [] args) throws InterruptedException{

        Flux<String> flux = Flux.interval(Duration.ofMillis(250)).map(input -> {
            if (input < 3) {
                return "tick " + input;
            }
            throw new RuntimeException("boom");
        }).onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);

        // Note that interval executes on a timer Scheduler by default. Assuming we want to run that example in a main
        // class, we add a sleep call here so that the application does not exit immediately without any value being produced.
        TimeUnit.MILLISECONDS.sleep(2100);

    }
}
