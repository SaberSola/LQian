package com.zl.reactor.FluxMethod.generate;

import reactor.core.publisher.Flux;

/**
 * The simplest form of programmatic creation of a Flux is through the generate method, which takes a generator function.
 * <p>
 * This is for synchronous and one-by-one emissions, meaning that the sink is a SynchronousSink and that its next()
 * method can only be called at most once per callback invocation. You can then additionally call error(Throwable) or
 * complete(), but this is optional.
 * <p>
 * The most useful variant is probably the one that also lets you keep a state that you can refer to in your sink usage
 * to decide what to emit next. The generator function then becomes a BiFunction<S, SynchronousSink<T>, S>, with <S> the
 * type of the state object. You have to provide a Supplier<S> for the initial state, and your generator function now
 * returns a new state on each round.
 */
public class Chapter1 {

    public static void main(String[] args) {
        Flux<String> flux = Flux.generate(
                // We supply the initial state value of 0.
                () -> 0,
                (state, sink) -> {
                    // We use the state to choose what to emit (a row in the multiplication table of 3).
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) {
                        // We also use it to choose when to stop.
                        sink.complete();
                    }
                    // We return a new state that we use in the next invocation (unless the sequence terminated in this one).
                    return state + 1;
                });

        flux.subscribe(System.out::println);
    }
}
