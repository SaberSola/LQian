package com.zl.reactor.FluxMethod.buffer;

import reactor.core.publisher.Flux;

import java.util.List;

public class Chapter1 {


    /**
     * 使用buffer 把FLux just的内容 转换成一个集合List
     * @param args
     */
    public static void main(String[] args) {
        Flux<List<String>> flux = Flux.just("green", "yellow", "blue", "purple")
                .buffer()
                .log();

        flux.subscribe(System.out::println);
    }
}
