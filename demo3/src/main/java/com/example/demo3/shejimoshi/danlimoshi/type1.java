package com.example.demo3.shejimoshi.danlimoshi;


/**
 *饿汉式（静态常量）
 * */
public class type1 {

    private final static type1 instance = new type1();

    private type1(){

    }

    public type1 getInstance(){
        return  instance;
    }

}
