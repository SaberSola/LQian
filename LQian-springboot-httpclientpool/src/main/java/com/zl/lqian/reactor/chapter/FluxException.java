package com.zl.lqian.reactor.chapter;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeoutException;

/**
 * 异常处理方法
 */
public class FluxException {

    public static void main(String[] args){

        Flux<String> s = Flux.just("key1", "key2")
                // 	For each key, we asynchronously call the external service
                .flatMap(FluxException::callExternalService)
                // If the external service call fails, we fallback to the cache for that key. Note that we always apply
                // the same fallback, whatever the source error, e, is.
                .onErrorResume(throwable -> FluxException.getFromCache());

        s.subscribe(System.out::println);

        s = Flux.just("timeout1", "unknown", "key2")
                .flatMap(FluxException::callExternalService)
                .onErrorResume(error -> { // The function allows dynamically choosing how to continue.
                    if (error instanceof TimeoutException) { // If the source times out, hit the local cache.
                        return getFromCache();
                    } else if (error instanceof UnknownKeyException) { // If the source says the key is unknown, create a new entry.
                        return registerNewEntry("DEFAULT");
                    } else {
                        // In all other cases, "re-throw".
                        return Flux.error(error);
                    }
                });

        s.subscribe(System.out::println);

    }


    private static Publisher<String> callExternalService(String key) {

        if ("unknown".equals(key)) {
            throw new UnknownKeyException("There's an exception here");
        }
        return s -> s.onNext("callExternalService-->" + key);

    }

    private static Publisher<String> getFromCache() {
        return s -> s.onNext("getFromCache-->");
    }

    private static Publisher<String> registerNewEntry(String content) {
        return s -> s.onNext("registerNewEntry-->" + content);
    }


    static class UnknownKeyException extends RuntimeException {

        public UnknownKeyException() {
            super();
        }

        public UnknownKeyException(String message) {
            super(message);
        }

        public UnknownKeyException(String message, Throwable cause) {
            super(message, cause);
        }

        public UnknownKeyException(Throwable cause) {
            super(cause);
        }

        protected UnknownKeyException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }


}


