package com.zl.reactor.handling_errors;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;

/**
 * Log or React on the Side
 * <p>
 * For cases where you want the error to continue propagating but you still want to react to it without modifying the
 * sequence (logging it, for instance) there is the doOnError operator. (Catch, log an error-specific message,
 * and re-throw). This operator, as well as all operators prefixed with doOn , are sometimes referred to as a
 * "side-effect". They let you peek inside the sequence’s events without modifying them.
 * (对于希望错误继续传播但仍然希望在不修改序列(例如，记录它)的情况下对其作出反应的情况，有doOnError操作符。(捕获、记录特定于错误的消息并
 * 重新抛出)。这个操作符以及所有以doOn为前缀的操作符有时被称为“副作用”。它们允许您在不修改序列事件的情况下查看序列事件)
 */
public class Chapter6 {

    /**
     * The following example ensures that, when we fallback to the cache, we at least log that the external service had
     * a failure. We can also imagine we have statistic counters to increment as an error side-effect.
     *
     * @param args
     */
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Chapter6.class.getName());
        LongAdder failureStat = new LongAdder();

        Flux<String> flux = Flux.just("unknown")
                .flatMap(Chapter6::callExternalService) // The external service call that can fail…​
                .doOnError(e -> {
                    failureStat.increment();
                    // …​is decorated with a logging side-effect…​
                    log.info("uh oh, falling back, service failed ");
                })
                .onErrorResume(e -> getFromCache()); // …​and then protected with the cache fallback.

        flux.subscribe(System.out::println);
    }

    private static Publisher<String> callExternalService(String key) {
        if ("unknown".equals(key)) {
            throw new RuntimeException("There's an exception here");
        }

        return s -> s.onNext("callExternalService-->" + key);
    }

    private static Publisher<String> getFromCache() {
        return s -> s.onNext("getFromCache-->");
    }
}
