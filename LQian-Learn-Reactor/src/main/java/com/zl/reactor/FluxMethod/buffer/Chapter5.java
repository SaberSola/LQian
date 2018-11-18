package com.zl.reactor.FluxMethod.buffer;

import reactor.core.publisher.Flux;

import java.util.HashSet;

/**
 * public final <C extends Collection<? super T>> Flux<C> buffer(int maxSize,int skip,Supplier<C> bufferSupplier)
 * <p>
 * Collect incoming values into multiple user-defined Collection buffers that will be emitted by the returned Flux each
 * time the given max size is reached or once this Flux completes. Buffers can be created with gaps, as a new buffer
 * will be created every time skip values have been emitted by the source
 * <p>
 * When maxSize < skip : dropping buffers
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/buffersizeskip.png
 * <p>
 * When maxSize > skip : overlapping buffers
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/buffersizeskipover.png
 * <p>
 * When maxSize == skip : exact buffers
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/buffersize.png
 */
public class Chapter5 {

    public static void main(String[] args) {
        testMaxSizeLessThanSkip();
//        testMaxSizeGreaterThanSkip();
//        testMaxSizeEqualSkip();
    }

    /**
     * When maxSize < skip : dropping buffers
     */
    private static void testMaxSizeLessThanSkip() {
        Flux<HashSet<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange")
                .buffer(2, 3, HashSet::new)
                .log();

        flux.subscribe(System.out::println); // onSubscribe(FluxBuffer.BufferSkipSubscriber)
    }

    /**
     * When maxSize > skip : overlapping buffers
     */
    private static void testMaxSizeGreaterThanSkip() {
        Flux<HashSet<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange")
                .buffer(3, 2, HashSet::new)
                .log();

        flux.subscribe(System.out::println); // onSubscribe(FluxBuffer.BufferOverlappingSubscriber)
    }

    /**
     * When maxSize == skip : exact buffers
     */
    private static void testMaxSizeEqualSkip() {
        Flux<HashSet<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange")
                .buffer(3, HashSet::new)
                .log();

        flux.subscribe(System.out::println); // onSubscribe(FluxBuffer.BufferExactSubscriber)
    }
}
