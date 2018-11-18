package com.zl.reactor.FluxMethod.generate;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * If your state object needs to clean up some resources, use the generate(Supplier<S>, BiFunction, Consumer<S>) variant
 * to clean up the last state instance.
 * <p>
 * In the case of the state containing a database connection or other resource that needs to be handled at the end of
 * the process, the Consumer lambda could close the connection or otherwise handle any tasks that should be done at the
 * end of the process.
 */
public class Chapter3 {

    public static void main(String[] args) {
        Flux<String> flux = Flux.generate(
                // we generate a mutable object as the state.
                AtomicLong::new,
                (state, sink) -> {
                    // We mutate the state here.
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);

                    if (i == 10) {
                        sink.complete();
                    }
                    // We return the same instance as the new state.
                    return state;
                },
                // We see the last state value (11) as the output of this Consumer lambda.
                (state) -> System.out.println("state: " + state));

        flux.subscribe(System.out::println);
    }
}
