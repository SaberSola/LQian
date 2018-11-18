package com.zl.reactor.debug;

import reactor.core.publisher.Flux;

/**
 * For instance, suppose we have logback activated and configured and a chain like range(1,10).take(3).
 * By placing a log() just before the take, we can get some insight into how it works and what kind of events it
 * propagates upstream to the range, as shown in the following example:
 * <p>
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/log.png
 * <p>
 * This prints out (through the logger’s console appender):
 * <p>
 * 10:45:20.200 [main] INFO  reactor.Flux.Range.1 - | onSubscribe([Synchronous Fuseable] FluxRange.RangeSubscription)
 * 10:45:20.205 [main] INFO  reactor.Flux.Range.1 - | request(unbounded)
 * 10:45:20.205 [main] INFO  reactor.Flux.Range.1 - | onNext(1)
 * 10:45:20.205 [main] INFO  reactor.Flux.Range.1 - | onNext(2)
 * 10:45:20.205 [main] INFO  reactor.Flux.Range.1 - | onNext(3)
 * 10:45:20.205 [main] INFO  reactor.Flux.Range.1 - | cancel()
 * <p>
 * Here, in addition to the logger’s own formatter (time, thread, level, message), the log() operator outputs a few
 * things in its own format:
 * <p>
 * 1.reactor.Flux.Range.1 is an automatic category for the log, in case you use the operator several times in a chain.
 * It allows you to distinguish which operator’s events are logged (in this case, the range). The identifier can be
 * overwritten with your own custom category by using the log(String) method signature. After a few separating
 * characters, the actual event gets printed. Here we get an onSubscribe call, an request call, three onNext calls, and
 * a cancel call. For the first line, onSubscribe, we get the implementation of the Subscriber, which usually
 * corresponds to the operator-specific implementation. Between square brackets, we get additional information,
 * including whether the operator can be automatically optimized via synchronous or asynchronous fusion.
 * <p>
 * 2.On the second line, we can see that an unbounded request was propagated up from downstream.
 * 3.Then the range sends three values in a row.
 * 4.On the last line, we see cancel().
 * <p>
 * The last line, (4), is the most interesting. We can see the take in action there. It operates by cutting the sequence
 * short after it has seen enough elements emitted. In short, take() causes the source to cancel() once it has emitted
 * the user-requested amount.
 */
public class Chapter1 {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1, 10)
                .log()
                .take(3);

        flux.subscribe();
    }
}
