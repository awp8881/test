package com.example.demo3.shejimoshi.danlimoshi;



/**
 *懒汉式（线程不安全）
 * */
public class type3 {

    private static type3 type;

    private type3(){}


    private static type3 getInstance(){
        if(type == null){
            type = new type3();
        }
        return  type;
    }

}
