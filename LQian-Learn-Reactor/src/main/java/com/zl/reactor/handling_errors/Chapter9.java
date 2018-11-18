package com.zl.reactor.handling_errors;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Retrying
 * <p>
 * There is another operator of interest with regards to error handling. retry, as its name indicates, lets you retry
 * an error-producing sequence.
 * <p>
 * The trouble is that it works by re-subscribing to the upstream Flux. This is really a different sequence, and the
 * original one is still terminated.
 * <p>
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/retryn.png
 * <p>
 * As you can see above, retry(1) merely re-subscribed to the original interval once, restarting the tick from 0. The
 * second time around, since the exception still occurs, it gives up and propagates the error downstream.
 * <p>
 * There is a more advanced version of retry (called retryWhen) that uses a "companion" Flux to tell whether or not a
 * particular failure should retry. This companion Flux is created by the operator but decorated by the user, in order
 * to customize the retry condition.
 * <p>
 * <p>
 * The companion Flux is a Flux<Throwable> that gets passed to a Function, the sole parameter of retryWhen. As the user,
 * you define that function and make it return a new Publisher<?>. Retry cycles will go like this:
 * 1.Each time an error happens (potential for a retry), the error is emitted into the companion Flux, which has been
 * decorated by your function.
 * 2.If the companion Flux emits something, a retry happens.
 * 3.If the companion Flux completes, the retry cycle stops and the original sequence completes too.
 * 4.If the companion Flux produces an error, the retry cycle stops and the original sequence also stops or completes,
 * and the error causes the original sequence to fail and terminate.
 * <p>
 * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/retrywhen.png
 */
public class Chapter9 {

    public static void main(String[] args) throws InterruptedException {
//        testRetry();
//        testRetryWhenOne();
        testRetryWhenTwo();
    }

    public static void testRetry() throws InterruptedException {
        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) {
                        return "tick " + input;
                    }
                    throw new RuntimeException("boom");
                })
                // elapsed associates each value with the duration since previous value was emitted.
                .elapsed()
                .retry(1)
                // We also want to see when there is an onError.
                .subscribe(System.out::println, System.err::println);

        // Ensure we have enough time for our 4x2 ticks.
        Thread.sleep(2100);
    }

    /**
     * The distinction between the previous two cases is important. Simply completing the companion would effectively
     * swallow an error. Consider the following way of emulating retry(4) using retryWhen:
     */
    public static void testRetryWhenOne() {
        Flux<String> flux = Flux
                // This continuously produces errors, calling for retry attempts.
                //这会不断产生错误，需要重试
                .<String>error(new IllegalArgumentException())
                // doOnError before the retry will let us see all failures.
                // 在重试前doOnError会让我们看到所有的失败
                .doOnError(System.out::println)
                // Here, we consider the first 4 errors as retry-able (take(4)) and then give up.
                // 在这里，我们将前4个错误视为可重试的(take(4))，然后剩下的错误将被放弃
                .retryWhen(companion -> companion.take(4));

        flux.subscribe(System.out::println, System.err::println);
    }

    /**
     * In effect, this results in an empty Flux, but it completes successfully. Since retry(4) on the same Flux would
     * have terminated with the latest error, this retryWhen example is not exactly the same as a retry(4).
     * <p>
     * Getting to the same behavior involves a few additional tricks
     * 要达到同样的行为需要一些额外的技巧
     * <p>
     * Similar code can be used to implement an exponential backoff and retry pattern, as shown in the FAQ.
     * https://projectreactor.io/docs/core/release/reference/#faq.exponentialBackoff
     * 类似的代码可以用来实现指数级回退和重试模式，如FAQ所示。
     */
    public static void testRetryWhenTwo() {
        Flux<String> flux =
                Flux.<String>error(new IllegalArgumentException())
                        .retryWhen(companion -> companion
                                // Trick one: use zip and a range of "number of acceptable retries + 1".
                                .zipWith(Flux.range(1, 5),
                                        // The zip function lets you count the retries while keeping track of the original error.
                                        // zip函数允许您在跟踪原始错误的同时计算重试次数
                                        (error, index) -> {
                                            // To allow for three retries, indexes before 5 return a value to emit.
                                            // 为了允许重试4次，5之前的索引返回一个值来发出
                                            if (index < 5) {
                                                return index;
                                            } else {
                                                // In order to terminate the sequence in error, we throw the original exception after these four retries.
                                                throw Exceptions.propagate(error);
                                            }
                                        })
                        );

        flux.subscribe(System.out::println, System.err::println);
    }
}
