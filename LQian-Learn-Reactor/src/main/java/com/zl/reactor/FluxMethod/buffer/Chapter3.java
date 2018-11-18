package com.zl.reactor.FluxMethod.buffer;

import reactor.core.publisher.Flux;

import java.util.HashSet;

/**
 * public final <C extends Collection<? super T>> Flux<C> buffer(int maxSize,Supplier<C> bufferSupplier)
 * <p>
 * Collect incoming values into multiple user-defined Collection buffers that will be emitted by the returned Flux each
 * time the given max size is reached or once this Flux completes.
 * <p>
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/buffersize.png
 */
public class Chapter3 {

    /**
     * 指定集合序列 重读的话 会覆盖前一个
     * @param args
     */
    public static void main(String[] args) {
        Flux<HashSet<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange")
                .buffer(3, HashSet::new)
                .log();

        flux.subscribe(System.out::println);
    }
}
