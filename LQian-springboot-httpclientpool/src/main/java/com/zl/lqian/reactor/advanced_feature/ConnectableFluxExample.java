package com.zl.lqian.reactor.advanced_feature;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

/**
 *
 * 使用 ConnectableFlux 对多个订阅者进行广播
 */
public class ConnectableFluxExample {

    public static void main(String[] args) throws InterruptedException{

        /**
         *    有时候，你不仅想要延迟到某一个订阅者订阅之后才开始发出数据，可能还希望在多个订阅者 到齐 之后 才开始。
         *
         *    Flux API 中有两种主要的返回 ConnectableFlux 的方式：publish 和 replay。
         *
         *    publish; 会尝试满足各个不同订阅者的需求（背压），并综合这些请求反馈给源。
         *             尤其是如果有某个订阅者的需求为 0，publish 会 暂停 它对源的请求。
         *
         *    replay: 将对第一个订阅后产生的数据进行缓存，最多缓存数量取决于配置（时间/缓存大小）。
         *            它会对后续接入的订阅者重新发送数据。
         *
         *    ConnectableFlux 提供了多种对下游订阅的管理。包括：
         *
         *    connect: 当有足够的订阅接入后，可以对 flux 手动执行一次。它会触发对上游源的订阅。
         *
         *    autoConnect(n) ; 与 connect 类似，不过是在有 n 个订阅的时候自动触发。
         *
         *    refCount(n) : 不仅能够在订阅者接入的时候自动触发，还会检测订阅者的取消动作。如果订阅者数量不够，
         *                  会将源“断开连接”，再有新的订阅者接入的时候才会继续“连上”源。
         *
         *    refCount(int, Duration): 增加了一个 "优雅的倒计时"：一旦订阅者数量太低了，
         *                            它会等待 Duration 的时间，如果没有新的订阅者接入才会与源“断开连接”。
         */
        //connectableFluxChapter();
        connectableFluxChapter2();
    }


    public static void connectableFluxChapter() throws InterruptedException{
        Flux<Integer> source  =  Flux.range(1,3)
                .doOnSubscribe(subscription -> System.out.println("subscribed to source"));
        ConnectableFlux<Integer> co  = source.publish();

        co.subscribe(System.out::println, e -> {
        }, () -> {
        });

        co.subscribe(System.out::println, e -> {
        }, () -> {
        });

        System.out.println("done subscribing");

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("will now connect");

        co.connect();
    }


    public static void connectableFluxChapter2() throws InterruptedException{

        Flux<Integer> source = Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("subscribed to source"));

        Flux<Integer> autoCo = source.publish().autoConnect(2); //当有俩个订阅的时候自动触发

        autoCo.subscribe(System.out::println, e -> {
        }, () -> {
        });

        System.out.println("subscribed first");

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("subscribing second");

        autoCo.subscribe(System.out::println, e -> {
        }, () -> {
        });
    }
}
