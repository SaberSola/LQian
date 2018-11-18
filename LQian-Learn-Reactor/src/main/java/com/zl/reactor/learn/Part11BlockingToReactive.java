package com.zl.reactor.learn;

import com.zl.reactor.learn.domain.User;
import com.zl.reactor.learn.repository.BlockingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part11BlockingToReactive {





    /**
     * Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed, and run
     * it with an elastic scheduler
     *
     * @param repository
     * @return
     */
    Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll()));   //defer  是一种冷发布
    }


    /**
     * Insert users contained in the Flux parameter in the blocking repository using an elastic scheduler and return a
     * Mono<Void> that signal the end of the operation
     * <p>
     * TODO not implement
     *
     * @param flux
     * @param repository
     * @return
     */
    Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {
        return null;
    }

}
