package com.zl.reactor.handling_errors;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrating the Terminal Aspect of onError
 * <p>
 * In order to demonstrate that all these operators cause the upstream original sequence to terminate when the error
 * happens, we can use a more visual example with a Flux.interval. The interval operator ticks every x units of time
 * with an increasing Long value:
 */
public class Chapter8 {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> flux = Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) {
                        return "tick " + input;
                    }
                    throw new RuntimeException("boom");
                })
                .onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);

        // Note that interval executes on a timer Scheduler by default. Assuming we want to run that example in a main
        // class, we add a sleep call here so that the application does not exit immediately without any value being produced.
        TimeUnit.MILLISECONDS.sleep(2100);
    }
}
