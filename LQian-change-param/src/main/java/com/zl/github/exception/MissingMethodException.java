package com.zl.github.exception;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public class MissingMethodException extends RuntimeException {

    public MissingMethodException() {
        super();
    }

    public MissingMethodException(final String message) {
        super(message);
    }
}
