package com.zl.reactor.learn;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class Part01Flux {


    /**
     *
     * 返回一个空的 Flux
     * @return
     */
    public Flux<String> emptyFlux (){return null;}

    /**
     * 返回一个包含俩个Value的 Flux
     *
     * @return
     */
    public Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }

    /**
     *
     * create a Flux form a List that contains tow valuse "foo" and "bar"
     * @return
     */
    public Flux<String> fooBarFluxFromList(){
        return Flux.fromIterable(Arrays.asList("foo","bar"));
    }

    /**
     * create a Flux emits(发出) an IllegalStateException
     * @return
     */
    public Flux<String> errorFlux(){
        return Flux.error(new IllegalStateException());
    }

    /**
     *  create a Flux that emits increasing values from 0 to 9 each 100ms
     * @return
     */
    public Flux<Long> counter() {
        return Flux.interval(Duration.ofMillis(100)).take(10);
    }
}
