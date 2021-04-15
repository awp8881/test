package com.example.demo3.controller;


import com.example.demo3.Error.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(BaseError.class)
    @ResponseBody
    public Map<String,Object> handleUserNotExitException(HttpServletRequest request, BaseError ex){
        Map<String,Object> map  = new HashMap();
        System.out.println(request.getServletPath());
        System.out.println(ex.getCause());
        map.put("id",ex.getError().getCode());
        map.put("message",ex.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> Exception(HttpServletRequest request, Exception ex){
        Map<String,Object> map  = new HashMap();
        System.out.println(request.getServletPath());
        System.out.println(ex.getCause());

        map.put("message",ex.getMessage());
        return map;
    }

}
