package com.zl.reactor.FluxMethod.buffer;

import reactor.core.publisher.Flux;

import java.util.List;

/**
 * public final Flux<List<T>> buffer(int maxSize,int skip)
 * <p>
 * Collect incoming values into multiple List buffers that will be emitted by the returned Flux each time the given max
 * size is reached or once this Flux completes. Buffers can be created with gaps, as a new buffer will be created every
 * time skip values have been emitted by the source.
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
public class Chapter4 {

    public static void main(String[] args) {
        //testMaxSizeLessThanSkip();
        //testMaxSizeGreaterThanSkip();
         testMaxSizeEqualSkip();
    }

    /**
     * When maxSize < skip : dropping buffers
     * skip 截取 max 截取的最大 skip 0 -3 中截取后俩个
     */
    private static void testMaxSizeLessThanSkip() {
        Flux<List<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange")
                .buffer(2, 3)
                .log();

        flux.subscribe(System.out::println); // onSubscribe(FluxBuffer.BufferSkipSubscriber)
    }

    /**
     * When maxSize > skip : overlapping buffers
     *
     * 缓存重叠 转成list容量为3 截取范围 0-2 所以有重叠 green yellow blue，  blue purple orange，orange
     */
    private static void testMaxSizeGreaterThanSkip() {
        Flux<List<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange")
                .buffer(3, 2)
                .log();

        flux.subscribe(System.out::println); // onSubscribe(FluxBuffer.BufferOverlappingSubscriber)
    }

    /**
     * When maxSize == skip : exact buffers
     */
    private static void testMaxSizeEqualSkip() {
        Flux<List<String>> flux = Flux.just("green", "yellow", "blue", "purple", "orange")
                .buffer(3)
                .log();

        flux.subscribe(System.out::println); // onSubscribe(FluxBuffer.BufferExactSubscriber)
    }
}
