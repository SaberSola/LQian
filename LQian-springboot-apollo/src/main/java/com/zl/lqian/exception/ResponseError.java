package com.zl.lqian.exception;

import java.sql.Timestamp;

public class ResponseError {
    private String status;   //异常状态码
    private String error;    //标准异常信息
    private String message;  //自定义异常信息
    private String exception; //异常类型

    private String timestamp ; //时间戳

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ResponseError() {
    }

    public ResponseError(String status, String error, String message, String exception) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.exception = exception;
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
    }

    public static ResponseError build (ResponseError.Type errorType, String message) {
        ResponseError error = new ResponseError();
        error.status = errorType.getStatus();
        error.error = errorType.getError();
        error.message = message;
        error.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
        return error;
    }

    public enum Type {
        BAD_REQUEST_ERROR("error.badrequest", "Bad request error"),
        INTERNAL_SERVER_ERROR("error.internalserver", "Unexpected server error"),
        VALIDATION_ERROR("error.validation", "Found validation issues");

        private String status;
        private String error;

        Type(String status, String error) {
            this.status = status;
            this.error = error;
        }

        public String getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }
    }
}
