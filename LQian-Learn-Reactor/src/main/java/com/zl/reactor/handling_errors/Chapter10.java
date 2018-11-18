package com.zl.reactor.handling_errors;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * Handling Exceptions in Operators or Functions
 * <p>
 * In general, all operators can themselves contain code that potentially trigger an exception or calls to a
 * user-defined callback that can similarly fail, so they all contain some form of error handling.
 * <p>
 * Reactor, however, defines a set of exceptions (such as OutOfMemoryError) that are always deemed fatal. See the
 * Exceptions.throwIfFatal method. These errors mean that Reactor cannot keep operating and are thrown rather than propagated.
 * <p>
 * Internally, there are also cases where an unchecked exception still cannot be propagated (most notably during the
 * subscribe and request phases), due to concurrency races that could lead to double onError or onComplete conditions.
 * When these races happen, the error that cannot be propagated is "dropped". These cases can still be managed to some
 * extent via customizable hooks, see Dropping Hooks.
 * https://projectreactor.io/docs/core/release/reference/#hooks-dropping
 * <p>
 * <p>
 * You may ask: "What about Checked Exceptions?"
 * If, for example, you need to call some method that declares it throws exceptions, you still have to deal with those
 * exceptions in a try-catch block. You have several options, though:
 * 1.Catch the exception and recover from it. The sequence continues normally.
 * 2.Catch the exception and wrap it into an unchecked exception, then throw it (interrupting the sequence).
 * The Exceptions utility class can help you with that.
 * 3.If you are expected to return a Flux (for example, you are in a flatMap), wrap the exception in an
 * error-producing Flux: return Flux.error(checkedException). (The sequence also terminates.)
 * <p>
 * Reactor has an Exceptions utility class that you can use to ensure that exceptions are wrapped only if they
 * are checked exceptions:
 * 1.Use the Exceptions.propagate method to wrap exceptions, if necessary. It also calls throwIfFatal first and does
 * not wrap RuntimeException.
 * 2.Use the Exceptions.unwrap method to get the original unwrapped exception (going back to the root cause of a
 * hierarchy of reactor-specific exceptions).
 */
public class Chapter10 {

    public static void main(String[] args) {
//        testOne();
        testTwo();
    }

    /**
     * As a rule of thumb, an Unchecked Exception is always propagated through onError. For instance, throwing a
     * RuntimeException inside a map function translates to an onError event, as shown in the following code:
     * <p>
     * The Exception can be tuned before it is passed to onError, through the use of a hook.
     * https://projectreactor.io/docs/core/release/reference/#hooks-internal
     */
    public static void testOne() {
        Flux.just("foo")
                .map(s -> {
                    throw new IllegalArgumentException(s);
                })
                .subscribe(v -> System.out.println("GOT VALUE"),
                        e -> System.out.println("ERROR: " + e));
    }

    /**
     * Consider an example of a map that uses a conversion method that can throw an IOException:
     * <p>
     * Now imagine that you want to use that method in a map. You must now explicitly catch the exception, and your map
     * function cannot re-throw it. So you can propagate it to the map’s onError method as a RuntimeException:
     * <p>
     * Later on, when subscribing to the following Flux and reacting to errors (such as in the UI), you could revert
     * back to the original exception in case you want to do something special for IOExceptions:
     */
    public static void testTwo() {
        Flux<String> converted = Flux
                .range(1, 10)
                .map(i -> {
                    try {
                        return convert(i);
                    } catch (IOException e) {
                        throw Exceptions.propagate(e);
                    }
                });

        converted.subscribe(
                v -> System.out.println("RECEIVED: " + v),
                e -> {
                    if (Exceptions.unwrap(e) instanceof IOException) {
                        System.out.println("Something bad happened with I/O");
                    } else {
                        System.out.println("Something bad happened");
                    }
                }
        );
    }

    public static String convert(int i) throws IOException {
        if (i > 3) {
            throw new IOException("boom " + i);
        }
        return "OK " + i;
    }
}
