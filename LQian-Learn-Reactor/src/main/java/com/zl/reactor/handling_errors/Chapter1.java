package com.zl.reactor.handling_errors;

import reactor.core.publisher.Flux;

/**
 * When subscribing, the onError callback at the end of the chain is akin to a catch block. There, execution skips to
 * the catch in case an Exception is thrown
 */
public class Chapter1 {

    public static void main(String[] args) {
        Flux<String> s = Flux.range(1, 10)
                // A transformation is performed that can throw an exception.
                .map(Chapter1::doSomethingDangerous)
                // If everything went well, a second transformation is performed.
                .map(Chapter1::doSecondTransform);

        s.subscribe(
                // Each successfully transformed value is printed out.
                value -> System.out.println("RECEIVED " + value),
                // In case of an error, the sequence terminates and an error message is displayed.
                error -> System.err.println("CAUGHT " + error)
        );

        // This is conceptually similar to the following try/catch block:
        try {
            for (int i = 1; i < 11; i++) {
                // 	If an exception is thrown here…​
                String v1 = doSomethingDangerous(i);
                // 	…​the rest of the loop is skipped…
                String v2 = doSecondTransform(v1);
                System.out.println("RECEIVED " + v2);
            }
        } catch (Throwable t) {
            // 	…​the execution goes straight to here.
            System.err.println("CAUGHT " + t);
        }
    }

    private static String doSomethingDangerous(Integer number) {
        if (number == 8) {
            throw new RuntimeException("There's an exception here");
        }

        return number.toString();
    }

    private static String doSecondTransform(String content) {
        return "number-->" + content;
    }
}
