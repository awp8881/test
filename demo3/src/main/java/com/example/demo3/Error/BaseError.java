package com.example.demo3.Error;

import org.springframework.http.HttpStatus;

public class BaseError extends RuntimeException{
    private MyErrorInfo error = new MyErrorInfo();

    public BaseError(int code, String message, HttpStatus status, Throwable e) {
        super(message,e);
        error.setStatus(status.value());
        error.setError(message);
        error.setCode(code);
    }
    public BaseError(int code, String message, HttpStatus status) {
        this(code, message, status, null);
    }

    public MyErrorInfo getError() {
        return error;
    }

    public void setError(MyErrorInfo error) {
        this.error = error;
    }
}
