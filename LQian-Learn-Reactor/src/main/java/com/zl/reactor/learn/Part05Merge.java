package com.zl.reactor.learn;


import com.zl.reactor.learn.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 合并俩个 flux
 */
public class Part05Merge {


    /**
     * Merge flux1 and flux2 values with interleave: 合并并交叉
     * @param flux1
     * @param flux2
     * @return
     */
    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {

        return Flux.merge(flux1,flux2);
    }


    /**
     * Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values):合并无交叉
     *
     * @param flux1
     * @param flux2
     * @return
     */
    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        return Flux.mergeSequential(flux1, flux2);
    }


    /**
     * Create a Flux containing the value of mono1 then the value of mono2
     *
     * @param mono1
     * @param mono2
     * @return
     */
    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return Flux.mergeSequential(mono1, mono2);
    }
}
