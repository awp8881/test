package com.example.demo3.shejimoshi.danlimoshi;



/**
 *双重检查
 * */
public class type5 {

    private static volatile type5 type;

    private type5(){}


    private static type5 getInstance(){
        if(type == null){
            synchronized(type5.class){
                if(type == null){
                    type = new type5();
                }
            }
        }
        return  type;
    }

}
