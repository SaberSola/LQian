package com.zl.reactor.FluxMethod.as;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Chapter1 {


    /**
     *
     * 视同from 从一个序列读出来赋给当前序列
     * @param args
     */
    public static void main(String[] args) {
        Mono<String> mono = Flux.just("zhanglei")
                .as(Mono::from)
                .log();

        mono.subscribe(System.out::println);
    }
}
