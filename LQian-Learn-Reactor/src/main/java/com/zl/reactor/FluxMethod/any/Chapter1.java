package com.zl.reactor.FluxMethod.any;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Chapter1 {


    /**
     *
     * 这里代表任意的只要符合规则都会返回True
     * @param args
     */
    public static void main(String[] args) {
        Mono<Boolean> mono = Flux.just("orange", "green")
                .any("green"::equals)
                .log();

        mono.subscribe(System.out::println);
    }

}

