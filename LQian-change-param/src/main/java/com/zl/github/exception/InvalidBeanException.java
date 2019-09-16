package com.zl.github.exception;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public class InvalidBeanException  extends  RuntimeException{

    public InvalidBeanException(final Throwable cause) {
        super(cause);
    }

    public InvalidBeanException(final String message) {
        super(message);
    }


    public InvalidBeanException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
