package com.zl.lqian.reactor.learn1;

import reactor.core.publisher.BaseSubscriber;
import org.reactivestreams.Subscription;

/**
 * 自定义Subscribe
 */
public class SampleSubscriber <T> extends BaseSubscriber<T> {


    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }


}
