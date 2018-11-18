package com.zl.reactor.learn;

import reactor.core.publisher.Mono;

public class Part02Mono {


    /**
     * create an empty Mono
     * @return
     */
     public Mono<String> emptyMono() {
        return Mono.empty();
    }


    /**
     * Return a Mono that never emits any signal
     *
     * @return
     */
    public Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

    /**
     *
     * create a Mono that contains a "foo" value
     * @return
     */
    public Mono<String> fooMono(){

        return Mono.just("String");
    }


    /**
     * Create a Mono that emits an IllegalStateException
     *
     * @return
     */
    public Mono<String> errorMono() {
        return Mono.error(new IllegalStateException());
    }
}
