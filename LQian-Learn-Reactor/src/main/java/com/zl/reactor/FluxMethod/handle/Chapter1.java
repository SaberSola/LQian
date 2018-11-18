package com.zl.reactor.FluxMethod.handle;


import reactor.core.publisher.Flux;

/**
 * handle 方法有些不同，它在 Mono 和 Flux 中都有。然而，它是一个实例方法 （instance method），
 * 意思就是它要链接在一个现有的源后使用（与其他操作符一样）。
 * 它与 generate 比较类似，因为它也使用 SynchronousSink，并且只允许元素逐个发出。
 * 然而，handle 可被用于基于现有数据源中的元素生成任意值，有可能还会跳过一些元素。
 * 这样，可以把它当做 map 与 filter 的组合。handle 方法签名如下：
 */
public class Chapter1 {

    public static void main(String[] args){


    Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
            .handle((i,sink)->{
                String letter = alphabet(i);
                if (letter != null) {
                    sink.next(letter);
                }
            });


        alphabet.subscribe(System.out::println);

    }


    public static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }

        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }
}
