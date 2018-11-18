package com.zl.reactor.handling_errors;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Dynamic Fallback Value
 * <p>
 * Even if you do not have an alternative safer way of processing your data, you might want to compute a fallback value
 * out of the exception you received. (catch and dynamically compute a fallback value).
 * <p>
 * For instance, if your return type has a variant dedicated to holding an exception (think Future.complete(T success)
 * vs Future.completeExceptionally(Throwable error)), you could instantiate the error-holding variant and pass the exception.
 */
public class Chapter4 {

    public static void main(String[] args) {
        Flux<String> s = Flux.just("key1", "key2")
                // 	For each key, we asynchronously call the external service
                .flatMap(Chapter4::callExternalService)
                .onErrorResume(error -> Mono.just( // The boilerplate creates a Mono from Mono.just with onErrorResume.
                        // You then wrap the exception into the ad hoc class or otherwise compute the value out of the exception.
                        MyWrapper.fromError(error)
                ));

        s.subscribe(System.out::println);
    }

    private static Publisher<String> callExternalService(String key) {
        if ("key1".equals(key)) {
            throw new RuntimeException("There's an exception here");
        }

        return s -> s.onNext("callExternalService-->" + key);
    }

    private static class MyWrapper {

        private static String fromError(Throwable error) {
            return error.getMessage();
        }
    }
}
