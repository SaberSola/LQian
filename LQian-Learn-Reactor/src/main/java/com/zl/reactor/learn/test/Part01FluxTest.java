package com.zl.reactor.learn.test;

import com.zl.reactor.learn.Part01Flux;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Part01FluxTest {


    Part01Flux workshop = new Part01Flux();

    public static void main(String[] args){
        Part01FluxTest part01FluxTest = new Part01FluxTest();
        part01FluxTest.countEach100ms();
    }

    @Test
    public void empty(){
        Flux<String> flux =workshop.emptyFlux();
        StepVerifier.create(flux).verifyComplete();  //产生了一个null
    }

    @Test
    public void fromValues() {
        Flux<String> flux = workshop.fooBarFluxFromValues();  //产生了value 为Foo bar 的序列
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .verifyComplete();
        flux.subscribe(System.out::println);
    }



    @Test
    public void fromList() {
        Flux<String> flux = workshop.fooBarFluxFromList();
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .verifyComplete();
        flux.subscribe(System.out::println);
    }

    @Test
    public void error() {
        Flux<String> flux = workshop.errorFlux();
        StepVerifier.create(flux)
                .verifyError(IllegalStateException.class);
    }

    @Test
    public void countEach100ms() {
        Flux<Long> flux = workshop.counter();
        StepVerifier.create(flux)
                .expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
                .verifyComplete();

        flux.subscribe(aLong -> System.out.println(aLong));
    }

}
