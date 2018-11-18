package com.zl.reactor.handling_errors;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeoutException;

/**
 * Fallback Method
 * <p>
 * If you want more than a single default value and you have an alternative safer way of processing your data, you can
 * use onErrorResume. (catch and execute an alternative path with a fallback method).
 * <p>
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/onerrorresumewith.png
 * <p>
 * For example, if your nominal process is fetching data from an external and unreliable service, but you also keep a
 * local cache of the same data that can be a bit more out of date but is more reliable, you could do the following:
 * <p>
 * Like onErrorReturn, onErrorResume has variants that let you filter which exceptions to fallback on, based either on
 * the exception’s class or a Predicate. The fact that it takes a Function also allows you to choose a different
 * fallback sequence to switch to, depending on the error encountered:
 */
public class Chapter3 {

    public static void main(String[] args) {
        Flux<String> s = Flux.just("key1", "key2")
                // 	For each key, we asynchronously call the external service
                .flatMap(Chapter3::callExternalService)
                // If the external service call fails, we fallback to the cache for that key. Note that we always apply
                // the same fallback, whatever the source error, e, is.
                .onErrorResume(throwable -> Chapter3.getFromCache());

        s.subscribe(System.out::println);


        s = Flux.just("timeout1", "unknown", "key2")
                .flatMap(Chapter3::callExternalService)
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
