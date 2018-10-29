package com.zl.lqian.reactor.advanced_feature;


import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * context 只与订阅有关 和threadLocal类似 区别是 threadlocal 与线程绑定
 * Context 只与flux或Mono绑定
 */
public class ContextDemo {


    @Test
    public void testContextOne(){

        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap(s -> Mono.subscriberContext() // We flatMap on the source element, materializing the Context with Mono.subscriberContext().
                        .map(ctx -> s + " " + ctx.get(key))) // We then use map to extract the data associated to "message" and concatenate that with the original word.
                // The chain of operators ends with a call to subscriberContext(Function) that puts "World" into the Context under the key "message".
                .subscriberContext(ctx -> ctx.put(key, "World"));

        StepVerifier.create(r)
                .expectNext("Hello World") // The resulting Mono<String> indeed emits "Hello World".
                .verifyComplete();
    }


}
