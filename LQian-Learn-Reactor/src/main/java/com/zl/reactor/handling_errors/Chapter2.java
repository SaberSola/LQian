package com.zl.reactor.handling_errors;

import reactor.core.publisher.Flux;

/**
 * Static Fallback Value
 * <p>
 * (catch and return a static default value is onErrorReturn)
 * <p>
 * You also have the option of filtering (choosing) when to recover with a default value versus letting the error
 * propagate, depending on the exception that occurred
 */
public class Chapter2 {

    public static void main(String[] args) {
        Flux<String> s = Flux.just(10)
                .map(Chapter2::doSomethingDangerous)
                .onErrorReturn("RECOVERED");

        s.subscribe(System.out::println);

        s = Flux.just(10)
                .map(Chapter2::doSomethingDangerous)
                .onErrorReturn(e -> e.getMessage().contains("exception"), "recovered10");

        s.subscribe(System.out::println);
    }

    private static String doSomethingDangerous(Integer number) {
        if (number == 10) {
            throw new RuntimeException("There's an exception here");
        }

        return number.toString();
    }
}
