package com.zl.reactor.advanced_feature;


import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.context.Context;
import reactor.util.function.Tuple2;

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


    /**
     *  context api
     *
     *  Context 是 不可变的（immutable）
     *
     *  put(Object key, Object value): 存储一个key-value 返回一个新的 Context 对象。
     *                                 你也可以用 putAll(Context) 方法将两个 context 合并为一个新的 context。
     *
     *  hasKey(Object key): 方法检查一个 key 是否已经存在。
     *
     *  getOrDefault(Object key, T defaultValue):  方法取回 key 对应的值（类型转换为 T）， 或在找不到这个 key 的情况下返回一个默认值。
     *
     *  getOrEmpty(Object key): 来得到一个 Optional<T> （context 会尝试将值转换为 T）。
     *
     *  delete(Object key): 删除 key 关联的值，并返回一个新的 Context。
     *
     *
     */

    @Test
    public void testContextTwo() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .subscriberContext(ctx -> ctx.put(key, "World")) // The Context is written to too high in the chain…​
                // As a result, in the flatMap, there’s no value associated to our key. A default value is used instead.
                .flatMap(s -> Mono.subscriberContext()     //这里是读取context
                        .map(ctx -> s + " " + ctx.getOrDefault(key, "Stranger")));   //合并返回一个新的context

        StepVerifier.create(r)
                .expectNext("Hello Stranger") // The resulting Mono<String> thus emits "Hello Stranger". //这里是验证
                .verifyComplete();

        r.subscribe(System.out::println);

    }

    @Test
    public void  testDemo(){

        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap( s -> Mono.subscriberContext()              //2: 对源调用 flatMap 用 Mono.subscriberContext() 方法拿到 Context。
                        .map( ctx -> s + " " + ctx.get(key)))        //3: 然后使用 map 读取关联到 "message" 的值，然后与原来的值连接。
                .subscriberContext(ctx -> ctx.put(key, "World"));    //1: 操作链以调用 subscriberContext(Function) 结尾，将 "World" 作为 "message" 这个 key 的 值添加到 Context 中。
               //上图的执行顺序 subscription  是从下游像上的
        StepVerifier.create(r)
                .expectNext("Hello World")
                .verifyComplete();
    }


    /**
     * 写入 与 读取 Context 的 相对位置 很重要：
     * 因为 Context 是不可变的，它的内容只能被上游的操作符看到，如下例所示：
     */
    @Test
    public void testContextThree() {
        String key = "message";

        Mono<String> r = Mono.subscriberContext() // 初始化一个
                .map(ctx -> ctx.put(key, "Hello")) // In a map we attempt to mutate it   我们尝试修改key的值  put太靠上 下边的ctx 看不到 所以得到默认值
                .flatMap(ctx -> Mono.subscriberContext()) // We re-materialize the Context in a flatMap  //再次初始化一个
                .map(ctx -> ctx.getOrDefault(key, "Default")); // We read the attempted key in the Context

        StepVerifier.create(r)
                .expectNext("Default") // The key was never set to "Hello".
                .verifyComplete();
        r.subscribe(System.out::println);

    }


    /**
     * 这里，首先 Context 中的 key 被赋值 "World"。
     * 然后订阅信号（subscription signal）向上游 移动，又发生了另一次写入。
     * 这次生成了第二个不变的 Context，里边的值是 "Reactor"。之后， 数据开始流动， flatMap 拿到最近的 Context ，也就是第二个值为 Reactor 的 Context。
     */
    @Test
    public void testContextFour() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap(s -> Mono.subscriberContext()
                        .map(ctx -> s + " " + ctx.get(key)))       //写入的顺序很重要，上游只能拿到最近的一次put
                .subscriberContext(ctx -> ctx.put(key, "Reactor")) // A write attempt on key "message".
                .subscriberContext(ctx -> ctx.put(key, "World")); // Another write attempt on key "message".

        StepVerifier.create(r)
                .expectNext("Hello Reactor") // The map only saw the value set closest to it (and below it): "Reactor".
                .verifyComplete();
    }

    @Test
    public void testContextFive() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap(s -> Mono.subscriberContext()
                        .map(ctx -> s + " " + ctx.get(key))) //3: 这个flatMap 看到的是第二次赋值
                .subscriberContext(ctx -> ctx.put(key, "Reactor")) // 2: 第二次赋值
                .flatMap(s -> Mono.subscriberContext()
                        .map(ctx -> s + " " + ctx.get(key))) // 4: 这个flatMap 是将上一次的结果与第一次赋值拼接 s 为上一次的结果  原因在于 Context 是与 Subscriber 关联的，而每一个操作符访问的 Context 来自其下游的 Subscriber。
                .subscriberContext(ctx -> ctx.put(key, "World")); // 1: 第一次赋值

        StepVerifier.create(r)
                .expectNext("Hello Reactor World") // The Mono emits "Hello Reactor World".
                .verifyComplete();


    }

    @Test
    public void testContextSix() {
        String key = "message";
        Mono<String> r =
                Mono.just("Hello")
                        .flatMap(s -> Mono.subscriberContext()
                                .map(ctx -> s + " " + ctx.get(key))          // 2
                        )
                        .flatMap(s -> Mono.subscriberContext()
                                .map(ctx -> s + " " + ctx.get(key))     // 4 ctx指的是 第三步中的xtx
                                // This subscriberContext does not impact anything outside of its flatMap
                                .subscriberContext(ctx -> ctx.put(key, "Reactor"))  // 3
                        )
                        // This subscriberContext impacts the main sequence’s Context
                        .subscriberContext(ctx -> ctx.put(key, "World"));  // 1

        StepVerifier.create(r)
                .expectNext("Hello World Reactor")
                .verifyComplete();
    }



    @Test
    public void testContextForLibraryReactivePut() {

        ReactiveHTTP http = new ReactiveHTTP();
        Mono<String> put = http.doPut("www.example.com", Mono.just("Walter"))
                .subscriberContext(Context.of(ReactiveHTTP.HTTP_CORRELATION_ID, "2-j3r9afaf92j-afkaf"))
                .filter(t -> t.getT1() < 300)
                .map(Tuple2::getT2);

        StepVerifier.create(put)
                .expectNext("PUT <Walter> sent to www.example.com with header X-Correlation-ID = 2-j3r9afaf92j-afkaf")
                .verifyComplete();

        put.subscribe(System.out::println);
    }
}
