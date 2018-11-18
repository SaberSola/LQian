package com.zl.reactor.learn;



import com.zl.reactor.learn.domain.User;
import com.zl.reactor.learn.repository.ReactiveRepository;
import com.zl.reactor.learn.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.test.StepVerifier;
import java.util.logging.Level;

import java.util.logging.Level;

/**
 *
 * Learn how to control demand
 */
public class Part06Request {



    ReactiveRepository<User> repository = new ReactiveUserRepository();
    /**
     * create a StepVerifier that initally request all values and expect 4 values to be received
     *
     * @param flux
     * @return
     */
    StepVerifier requestAllExpectFour(Flux<User> flux){

        return StepVerifier.create(flux.log(), 4)
                .expectNextCount(4)
                .expectComplete();
    }


    /**
     *
     * create a StepVerifier that initially requests  1 value and expect User.SKYLER then request another value and
     * expect User.User.JESSE
     * @param flux
     * @return
     */
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux.log(), 1)
                .expectNext(User.SKYLER)
                .thenRequest(1)
                .expectNext(User.JESSE)
                .thenRequest(2)
                .expectNextCount(2)
                .expectComplete();
    }

    /**
     * Return a Flux with all users stored in the repository that prints "Starting:" on subscribe, "firstname lastname"
     * for all values and "The end!" on complete
     *
     * @return
     */
    Flux<User> fluxWithDoOnPrintln() {
        return repository.findAll()
                .doOnSubscribe(subscription -> System.out.println("Starting:"))
                .doOnNext(user -> System.out.println(user.getFirstname() + " " + user.getLastname()))
                .doOnComplete(() -> System.out.println("The end!"));

    }

}
