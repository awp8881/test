package com.example.demo3.shejimoshi.danlimoshi;



/**
 *饿汉式（静态代码块）
 * */
public class type2 {

    private static  type2 type;

    private  type2(){}

    static {
        type = new type2();
    }

    private  type2 getInstance(){
        return  type;
    }

}
