package com.zl.reactor.advanced_feature;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 *  ParallelFlux 进行并行处理
 */
public class ParallelUsage {


    public static void main(String[] args){

        /**
         *
         *  Flux 使用 parallel() 操作符来得到一个 ParallelFlux. 不过这个操作符本身并不会进行并行处理，
         *  而是将负载划分到多个“轨道（rails）”上 （默认情况下，轨道个数与 CPU 核数相等）。
         *
         *  为了配置 ParallelFlux 如何并行地执行每一个轨道，
         *  你需要使用 runOn(Scheduler)。 注意，Schedulers.parallel() 是推荐的专门用于并行处理的调度器。
         *
         *  如果在并行地处理之后，需要退回到一个“正常”的 Flux 而使后续的操作链按非并行模式执行，
         *  你可以对 ParallelFlux 使用 sequential() 方法。
         *
         *  注意，当你在对 ParallelFlux 使用一个 Subscriber 而不是基于 lambda 进行订阅（subscribe()） 的时候，sequential() 会自动地被偷偷应用
         *
         *  注意 subscribe(Subscriber<T>) 会合并所有的执行轨道，而 subscribe(Consumer<T>) 会在所有轨道上运行。
         *  如果 subscribe() 方法中是一个 lambda，那么有几个轨道，lambda 就会被执行几次。
         *
         *
         */
       // parallelOne();
        paralleTwo();
    }

    /**
     * 这个其实就是主线程
     */
    public static void parallelOne(){
        Flux.range(1, 10)
                .parallel(2)
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }

    public static void paralleTwo(){
        Flux.range(1, 10)
                .parallel(2)
                .runOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }
}
