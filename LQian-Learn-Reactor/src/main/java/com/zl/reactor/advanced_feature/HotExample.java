package com.zl.reactor.advanced_feature;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

/**
 * 热发布: 不管有没有订阅 创建完成就发
 *
 */
public class HotExample {


    public static void main(String[] args){
        UnicastProcessor<String> hotSource = UnicastProcessor.create();
        Flux<String> hotFlux = hotSource.publish().
                autoConnect().
                map(String::toUpperCase);
        hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: " + d));
        hotSource.onNext("blue");
        hotSource.onNext("green");

        hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: " + d));
        hotSource.onNext("orange");
        hotSource.onNext("purple");
        hotSource.onComplete();

    }
}
