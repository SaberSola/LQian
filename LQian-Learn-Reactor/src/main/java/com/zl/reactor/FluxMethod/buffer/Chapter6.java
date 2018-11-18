package com.zl.reactor.FluxMethod.buffer;

import reactor.core.publisher.Flux;

import java.util.List;

/**
 * public final Flux<List<T>> buffer(Publisher<?> other)
 * <p>
 * Collect incoming values into multiple List buffers, as delimited by the signals of a companion Publisher this
 * operator will subscribe to.
 * <p>
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/bufferboundary.png
 */
public class Chapter6 {

    // TODO not implement
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("green", "yellow", "blue", "purple", "orange");

        Flux<List<String>> listFlux = flux
                .buffer()
                .log();

        listFlux.subscribe(System.out::println);
    }
}
