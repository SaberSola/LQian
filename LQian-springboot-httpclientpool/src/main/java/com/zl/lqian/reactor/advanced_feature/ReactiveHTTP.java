package com.zl.lqian.reactor.advanced_feature;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Optional;

/**
 *  一个响应式的 HTTP 客户端将一个 Mono<String> （用于 PUT 请求）作为数据源，
 *  同时通过一个特定的 key 使用 Context 将关联的ID信息放入请求头中。
 */
public class ReactiveHTTP {

    public static final String HTTP_CORRELATION_ID = "reactive.http.library.correlationId";


    /**
     * Tuple2  是一种元祖集合
     * @param url
     * @param data
     * @return
     */
    public Mono<Tuple2<Integer, String>> doPut(String url, Mono<String> data) {

        Mono<Tuple2<String, Optional<Object>>> dataAndContext =
                data.zipWith(Mono.subscriberContext() // zipWith 合并并处理数据  	用 Mono.subscriberContext() 拿到 Context
                        .map(c -> c.getOrEmpty(HTTP_CORRELATION_ID)));    //	提取出关联ID的值——是一个 Optional。

        return dataAndContext.<String>handle((dac,sink)->{

            if (dac.getT2().isPresent()){             //如果值存在，那么就将其加入请求头。
                sink.next("PUT <" + dac.getT1() + "> sent to " + url + " with header X-Correlation-ID = " + dac.getT2().get());
            }else  {
                sink.next("PUT <" + dac.getT1() + "> sent to " + url);
            }
            sink.complete();

        }).map(msg-> Tuples.of(200, msg));

    }
}
