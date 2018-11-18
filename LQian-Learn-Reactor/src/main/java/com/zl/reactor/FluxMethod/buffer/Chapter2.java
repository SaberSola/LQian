package com.zl.reactor.FluxMethod.buffer;

import reactor.core.publisher.Flux;

import java.util.List;

public class Chapter2 {


    /**
     *
     * buffer(3) 截取前3个转换为list 剩下的转为一个list
     * @param args
     */
    public static void main(String[] args) {
        Flux<List<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange","dsafas","fasdfasf")
                .buffer(3)
                .log();

        flux.subscribe(System.out::println);
    }

}
