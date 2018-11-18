package com.zl.reactor.learn;

import com.zl.reactor.learn.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part04Transform {



    Mono<User> capitalizeOne(Mono<User> mono){

        /**
         * 返回一个新的mono
         */
        Mono<User> monoresult = mono.map(u -> new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
        return monoresult;
    }

    Flux<User> capitalizeMany(Flux<User> flux){
        return flux.map(u -> new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }


    /**
     * concatMap()
     * concatMap 操作符的作用也是把流中的每个元素转换成一个流，再把所有流进行合并。
     * 与 flatMap 不同的是，concatMap 会根据原始流中的元素顺序依次把转换之后的流进行合并；
     * 与 flatMapSequential 不同的是，concatMap 对转换之后的流的订阅是动态进行的，而 flatMapSequential 在合并之前就已经订阅了所有的流。
     * @param flux
     * @return
     */
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.concatMap(this::asyncCapitalizeUser);
    }


    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }
}
