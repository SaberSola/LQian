package com.zl.reactor.FluxMethod.all;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Chapter1 {


    public static void main(String[] args) {
        Mono<Boolean> mono = Flux.just("green", "yellow", "blue", "purple", "orange")
                .all(color -> color.length() >= 4)
                .log();
        mono.subscribe(System.out::println);
    }
}
