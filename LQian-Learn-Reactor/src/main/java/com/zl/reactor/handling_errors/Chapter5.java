package com.zl.reactor.handling_errors;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * Catch and Rethrow
 * <p>
 * In the "fallback method" example, the last line inside the flatMap gives us a hint as to how (Catch, wrap to a
 * BusinessException, and re-throw) could be achieved
 */
public class Chapter5 {

    public static void main(String[] args) {
        Flux<String> s = Flux.just("timeout1")
                .flatMap(Chapter5::callExternalService)
                .onErrorResume(original -> Flux.error(
                        new BusinessException("oops, SLA exceeded", original)
                ));

        s.subscribe(System.out::println);

        // However, there is a more straightforward way of achieving the same with onErrorMap:
        s = Flux.just("timeout1")
                .flatMap(Chapter5::callExternalService)
                .onErrorMap(original -> new BusinessException("oops, SLA exceeded", original));

        s.subscribe(System.out::println);
    }

    private static Publisher<String> callExternalService(String key) {
        if ("timeout1".equals(key)) {
            throw new RuntimeException("There's an exception here");
        }

        return s -> s.onNext("callExternalService-->" + key);
    }

    static class BusinessException extends RuntimeException {

        public BusinessException() {
            super();
        }

        public BusinessException(String message) {
            super(message);
        }

        public BusinessException(String message, Throwable cause) {
            super(message, cause);
        }

        public BusinessException(Throwable cause) {
            super(cause);
        }

        protected BusinessException(String message, Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
