package com.zl.lqian.client.exception;

public class CanalClientException extends RuntimeException {

    public CanalClientException(){}

    public CanalClientException(String message) {
        super(message);
    }

    /**
     * @param message 错误信息
     * @param cause   错误原因
     */
    public CanalClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalClientException(Throwable cause) {
        super(cause);
    }

    public CanalClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
