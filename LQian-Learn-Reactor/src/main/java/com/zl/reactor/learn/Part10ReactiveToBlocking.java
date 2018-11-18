package com.zl.reactor.learn;


import com.zl.reactor.learn.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to turn Reactive API to blocking one.
 */
public class Part10ReactiveToBlocking {

    /**
     * Return the user contained in that Mono
     *
     * @param mono
     * @return
     */
    User monoToValue(Mono<User> mono) {
        return mono.block();
    }

    /**
     * Return the users contained in that Flux
     *
     * @param flux
     * @return
     */
    Iterable<User> fluxToValues(Flux<User> flux) {
        return flux.toIterable();
    }
}
