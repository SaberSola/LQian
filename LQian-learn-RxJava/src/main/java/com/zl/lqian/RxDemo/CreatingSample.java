package com.zl.lqian.RxDemo;

import org.junit.Test;
import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.*;
import rx.schedulers.Schedulers;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author zl
 * @Date 2018-12-12
 * @Des ${todo}
 */
public class CreatingSample {


    @Test
    public void create() {

        Observable observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {   //只有有观察者订阅的时候才会执行
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });

    }

    /**
     * 直到有观察者订阅时才创建Observable，
     * 并且为每个观察者创建一个新的Observable
     * <p>
     * 虽然订阅者订阅的是同一个observable
     * 事实上每个订阅者获取的是它们自己的单独的数据序列。
     */
    @Test
    public void defer() {

        Observable<Integer> observable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.just(1, 2, 3, 4, 5);
            }
        });

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("emit nothing before subscribe");
        //开始被订阅
        observable.subscribe(
                new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.err.println("onError: " + e);
                    }

                    @Override
                    public void onNext(final Integer s) {
                        System.out.println("onNext: " + s);
                    }
                }
        );
        observable.subscribe(
                new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.err.println("onError: " + e);
                    }

                    @Override
                    public void onNext(final Integer s) {
                        System.out.println("TwoNext: " + s);
                    }
                }
        );
    }

    @Test
    public void from1() {

        Integer[] data = new Integer[]{1, 2, 3, 4, 5};
        Observable observable = Observable.from(data);
        observable.subscribe(new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError: " + e);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext: " + o);
            }
        });

    }

    public void from2() {

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add("List Item " + i);
        }
        Observable.from(list).subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.err.println("onError: " + e);
                    }

                    @Override
                    public void onNext(final String s) {
                        System.out.println("onNext: " + s);
                    }
                }
        );
    }

    @Test
    public void from3() {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Callable";
            }
        };
        final Future<String> future = executor.submit(callable);
        Observable.from(future).subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.err.println("onError: " + e);
                    }

                    @Override
                    public void onNext(final String s) {
                        System.out.println("onNext: " + s);
                    }
                }
        );
        System.out.println("system exit ");
    }

    @Test
    public void interval() {
        Observable.interval(2, TimeUnit.SECONDS, Schedulers.immediate()).take(3).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.err.println("onError: " + e);
                    }

                    @Override
                    public void onNext(final Long s) {
                        System.out.println("onNext: " + s + " time: "
                                + System.currentTimeMillis() / 1000);
                    }
                }
        );
    }

    @Test
    public void just() {
        Observable.just(1, 2, 3, 4, 5).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(final Throwable e) {
                System.err.println("onError: " + e);
            }

            @Override
            public void onNext(final Integer i) {
                System.out.println("onNext: " + i);
            }
        });
    }

    /**
     * range 是有范围的
     */
    @Test
    public void range() {
        Observable.range(100, 5).subscribe(
                new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.err.println("onError: " + e);
                    }

                    @Override
                    public void onNext(final Integer s) {
                        System.out.println("onNext: " + s);
                    }
                }
        );
    }

    /**
     * Repeat重复地发射数据。某些实现允许你重复的发射某个数据序列，
     * 还有一些允许你限制重
     * 复的次数。
     */
    @Test
    public void repeat() {

        Observable<String> observable = Observable.just("one")
                .repeat(5);
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(final Throwable e) {
                System.err.println("onError: " + e);
            }

            @Override
            public void onNext(final String s) {
                System.out.println("onNext: " + s);
            }
        });

    }

    public static void repeatWhen() {
        Observable.just("One").repeatWhen(
                new Func1<Observable<? extends Void>, Observable<?>>() {
                    @Override
                    public Observable<?> call(final Observable<? extends Void> observable) {
                        return observable.take(3);
                    }
                }).subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.err.println("onError: " + e);
                    }

                    @Override
                    public void onNext(final String s) {
                        System.out.println("onNext: " + s);
                    }
                }
        );
    }

    @Test
    public void buffer() {

        Observable<List<String>> observable = Observable.just("one", "two", "three").buffer(10);
        observable.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError: " + e);
            }

            @Override
            public void onNext(List<String> strings) {
                strings.forEach(s -> {
                    System.out.println("onNext: " + s);
                });

            }
        });
    }

    /**
     * 转换了Observable
     */
    @Test
    public void flatMap() {

        Observable observable = Observable.just("one").flatMap(new Func1<String, Observable<?>>() {
            @Override
            public Observable<?> call(String s) {
                return Observable.just(s + "zhanglei");
            }
        });
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
    }

    /**
     * 抓换值
     */
    @Test
    public void map() {
        Observable observable = Observable.just("one").map(new Func1<String, Object>() {

            @Override
            public Object call(String s) {
                return s + "zhangleia";
            }
        });
        observable.subscribe(new Action1<Object>() {

            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
    }

    /**
     * Scan 操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为
     * 自己的第一项数据发射。它将函数的结果同第二项数据一起填充给这个函数来产生它自己的
     * 第二项数据。它持续进行这个过程来产生剩余的数据序列
     */
    @Test
    public void scan() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        return sum + item;
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }

    @Test
    public void filter() {
        Observable.just(1, 2, 3, 4, 5)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer item) {
                        return (item < 4);
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }

    /**
     * 只发射第一项
     */
    @Test
    public void first() {

        Observable.just(1, 2, 3, 4, 5).first().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void firstOrDefault() {
        Observable.just(1, 2, 3, 4, 5).firstOrDefault(5, new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 100;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void last() {

        Observable.just(1, 2, 3)
                .last()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    /**
     * 插入一个 1 变成 1，2，3
     */
    @Test
    public void stratwith() {

        Observable.just(2, 3).startWith(1).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    /**
     * 打包操作
     */
    @Test
    public void zip() {

        Observable<Integer> observable = Observable.just(1, 2, 3);
        Observable<Integer> observ = Observable.just(2, 3, 4);
        Observable.zip(observable, observ, new Func2<Integer, Integer, Object>() {

            @Override
            public Object call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Object>() {

            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
    }

    @Test
    public void materialize() {
        Observable.just(1, 2, 3).materialize().subscribe(new Subscriber<Notification<Integer>>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Notification<Integer> integerNotification) {

                System.out.println(integerNotification.getValue());
            }
        });
    }

    /**
     * 和上边的类似于序列化和反序列化
     */
    @Test
    public void dematerialize() {
        Observable<Integer> observable = Observable.just(1, 2, 3).materialize().dematerialize();
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integerNotification) {

                System.out.println(integerNotification);
            }
        });
    }

    @Test
    public void delay() {
        Observable<Integer> obs = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                  /*  if(i>2){
                        subscriber.onError(new Throwable("VALUE TO MAX"));
                    }*/
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        /*
         * Delay操作符让原始Observable在发射每项数据之前都暂停一段指定的时间段。
         * 效果是Observable发射的数据项在时间上向前整体平移了一个增量
         *
         * 注意：delay不会平移onError通知，它会立即将这个通知传递给订阅者，同时丢弃任何待发射的onNext通知。
         * 然而它会平移一个onCompleted通知。
         */
        obs.delay(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("delay onCompleted" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("delay onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("delay onNext:" + sdf.format(new Date()) + "->" + integer);
                    }
                });

        /**
         * 延迟订阅
         */
        obs.delaySubscription(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("delaySubscription onCompleted" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("delaySubscription onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("delaySubscription onNext:" + sdf.format(new Date()) + "->" + integer);
                    }
                });
    }


    /**
     * do doeach doOnNext
     */
    @Test
    public void dodemo() {

        Observable.just(1, 2, 3).doOnEach(new Action1<Notification<? super Integer>>() {
            @Override
            public void call(Notification<? super Integer> notification) {
                System.out.println(notification.getValue());
            }
        }).subscribe();

        Observable.just(1, 2, 3)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer item) {
                        if (item > 1) {
                            throw new RuntimeException("Item exceeds maximum value");
                        }
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }
    @Test
    public void doOnSubscribe(){

        Observable.just(1,2,3).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("被订阅");
            }
        }).doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("被un订阅");
            }
        }).subscribe().unsubscribe();
    }

    /**
     * 所有的全部满足条件才会返回True
     */
    @Test
    public void testAll(){
        Observable.just(1,2,3).all(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 2;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println(aBoolean);
            }
        });
    }

    /**
     * 只会接收一个
     */
    @Test
    public void testAmp(){
        Iterable<Observable<Integer>> observables = new ArrayList<>();
        ((ArrayList<Observable<Integer>>) observables).add(Observable.just(2,2,3));
        ((ArrayList<Observable<Integer>>) observables).add(Observable.just(1,3,4));
        ((ArrayList<Observable<Integer>>) observables).add(Observable.just(2,6,6));
        Observable.amb(observables).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }
}
