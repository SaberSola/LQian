package com.zl.lqian.eventbus;

public class ConcurrentThreadLocal {

    private static final ThreadLocal<String> CURRENT_LOCAL = new ThreadLocal<>();

    private static final ConcurrentThreadLocal TRANSACTION_CONTEXT_LOCAL = new ConcurrentThreadLocal();

    private ConcurrentThreadLocal() {

    }
    public static ConcurrentThreadLocal getInstance() {
        return TRANSACTION_CONTEXT_LOCAL;
    }

    /**
     * set value.
     * @param context context
     */
    public void set(final String context) {
        CURRENT_LOCAL.set(context);
    }

    /**
     * get value.
     * @return TccTransactionContext
     */
    public String get() {
        return CURRENT_LOCAL.get();
    }

    /**
     * clean threadLocal for gc.
     */
    public void remove() {
        CURRENT_LOCAL.remove();
    }

}
