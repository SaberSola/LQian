package com.zl.lqian.reactor.advanced_feature;

import reactor.core.publisher.Flux;

import java.util.Arrays;

/**
 * 冷发布: 只有订阅者存在的时候才开始发布
 *         否则不发布
 */
public class ColdExample {


    public static void main(String[] args){

        Flux<String> source = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .doOnNext(System.out::println)
                .filter(s -> s.startsWith("o"))
                .map(String::toUpperCase);

        source.subscribe(d -> System.out.println("Subscriber 1: " + d));
        source.subscribe(d -> System.out.println("Subscriber 2: " + d));
    }
}
