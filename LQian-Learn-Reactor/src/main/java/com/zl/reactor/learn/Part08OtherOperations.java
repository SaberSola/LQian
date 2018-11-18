package com.zl.reactor.learn;

import com.zl.reactor.learn.domain.User;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

public class Part08OtherOperations {



    /**
     * Create a Flux of user from Flux of username, firstname and lastname.
     *
     * @param usernameFlux
     * @param firstnameFlux
     * @param lastnameFlux
     * @return
     */
    Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
        return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)      //压缩成一个
                .map(TupleUtils.function(User::new));
    }


    /**
     * Return the mono which returns its value faster
     * <p>
     * Pick the first {@link Mono} to emit any signal (value, empty completion or error)
     * and replay that signal, effectively behaving like the fastest of these competing sources.
     * <p>
     * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/first.png
     *
     * @param mono1
     * @param mono2
     * @return
     */
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return Mono.first(mono1, mono2);           //return 响应最快的
    }


    /**
     * Return the flux which returns the first value faster
     * <p>
     * Pick the first {@link Publisher} to emit any signal (onNext/onError/onComplete) and
     * replay all signals from that {@link Publisher}, effectively behaving like the
     * fastest of these competing sources.
     * <p>
     * https://raw.githubusercontent.com/reactor/reactor-core/v3.1.0.RC1/src/docs/marble/firstemitting.png
     *
     * @param flux1
     * @param flux2
     * @return
     */
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return Flux.first(flux1, flux2);
    }



    /**
     * Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
     * <p>
     * Return a {@code Mono<Void>} that completes when this {@link Flux} completes.
     * This will actively ignore the sequence and only replay completion or error signals.
     *
     * @param flux
     * @return
     */
    Mono<Void> fluxCompletion(Flux<User> flux) {
        return flux.then();
    }



    /**
     * Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
     *
     * @param user
     * @return
     */
    Mono<User> nullAwareUserToMono(User user) {   //将user 返回一个Mono
        return Mono.justOrEmpty(user);
    }


    /**
     * Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
     *
     * @param mono
     * @return
     */
    Mono<User> emptyToSkyler(Mono<User> mono) {
        return mono.defaultIfEmpty(User.SKYLER);
    }  //给个默认值

}
