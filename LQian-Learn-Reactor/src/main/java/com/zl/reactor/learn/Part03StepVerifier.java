package com.zl.reactor.learn;

import com.zl.reactor.learn.domain.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

public class Part03StepVerifier {


    /**
     * use StepVerifier to check tht Flux paramters meits "foo" and "bar" elements then completes successfully
     *
     * @param flux
     */
    void expectFooBarComplete(Flux<String> flux) {

        StepVerifier.create(flux)
                .expectNext("foo","bar")
                .verifyComplete();
    }


    /**
     * Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
     *
     * @param flux
     */
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .expectError(RuntimeException.class)
                .verify();
    }


    /**
     * Use StepVerifier to check that the flux parameter emits a User with "swhite" username
     * and another one with "jpinkman" then completes successfully.
     *
     * @param flux
     */
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
                .expectNextMatches(user -> "swhite".equals(user.getUsername()))
                .expectNextMatches(user -> "jpinkman".equals(user.getUsername()))
                .verifyComplete();
    }

    /**
     * Expect 10 elements then complete and notice how long the test takes.
     *
     * @param flux
     */
    void expect10Elements(Flux<Long> flux) {
        StepVerifier.create(flux)
                .expectNextCount(10)
                .verifyComplete();
    }


    /**
     * Expect 20 elements at intervals(间隔) of 1 second, and verify quicker than 20s.
     * by manipulating（操作） virtual(实质上的) time thanks to StepVerifier#withVirtualTime, notice how long the test takes.
     *
     * @param supplier
     */
    void expect20Elements(Supplier<Flux<Long>> supplier) {
        StepVerifier.withVirtualTime(supplier, 20)
                .thenAwait(Duration.ofSeconds(5))
                .expectNextCount(20);
    }

}
