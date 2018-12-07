package com.zl.lqian.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MdcHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {


    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new MdcAwareCallable(callable, MDC.getCopyOfContextMap());
    }

    private class MdcAwareCallable<T> implements Callable<T> {

        private final Callable<T> delegate;

        private final Map<String, String> contextMap;

        public MdcAwareCallable(Callable<T> callable, Map<String, String> contextMap) {
            this.delegate = callable;
            this.contextMap = contextMap != null ? contextMap : new HashMap();
        }

        @Override
        public T call() throws Exception {
            try {
                MDC.setContextMap(contextMap);
                return delegate.call();
            } finally {
                MDC.clear();
            }
        }
    }
}
