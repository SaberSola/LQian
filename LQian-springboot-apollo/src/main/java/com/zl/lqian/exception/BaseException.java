package com.zl.lqian.exception;

public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    private ResponseError responseError;

    public ResponseError getResponseError() {
        return responseError;
    }

    public void setResponseError(ResponseError responseError) {
        this.responseError = responseError;
    }
}
