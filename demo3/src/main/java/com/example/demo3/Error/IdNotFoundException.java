package com.example.demo3.Error;

import org.springframework.http.HttpStatus;

public class IdNotFoundException extends BaseError{
    public IdNotFoundException(){
        super(40400, "请求资源不存在", HttpStatus.NOT_FOUND);
    }

    public IdNotFoundException(String message) {
        super(40400, message, HttpStatus.NOT_FOUND);
    }

    public IdNotFoundException(int code, String message) {
        super(code, message, HttpStatus.NOT_FOUND);
    }

    public static IdNotFoundException byId(String id){
        return  new IdNotFoundException(40401,String.format("请求资源 %s 不存在", id));
    }
}
