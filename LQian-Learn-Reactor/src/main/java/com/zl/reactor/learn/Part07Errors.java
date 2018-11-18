package com.zl.reactor.learn;

import com.zl.reactor.learn.domain.User;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part07Errors {



    /**
     * Return a Mono<User> containing User.SAUL when an error occurs in the input Mono,
     * else do not change the input Mono.
     *
     * @param mono
     * @return
     */
    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
        return mono.onErrorReturn(User.SAUL);
    }

    /**
     * Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux,
     * else do not change the input Flux.
     *
     * @param flux
     * @return
     */
    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return flux.onErrorResume(throwable -> Flux.just(User.SAUL, User.JESSE));
    }

    /**
     * Implement a method that capitalizes each user of the incoming flux using the #capitalizeUser method and emits an
     * error containing a GetOutOfHereException error
     *
     * @param flux
     * @return
     */
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(user -> {
            try {
                return this.capitalizeUser(user);
            } catch (GetOutOfHereException e) {
                throw Exceptions.propagate(e);
            }
        });
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {
    }
}
