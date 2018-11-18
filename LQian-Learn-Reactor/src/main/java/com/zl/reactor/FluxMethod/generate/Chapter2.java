package com.zl.reactor.FluxMethod.generate;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * You can also use a mutable <S>. The example could for instance be rewritten using a single AtomicLong as the state,
 * mutating it on each round
 */
public class Chapter2 {

    public static void main(String[] args) {
        Flux<String> flux = Flux.generate(
                // This time, we generate a mutable object as the state.
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
                });

        flux.subscribe(System.out::println);
    }
}
