package com.example.demo3.shejimoshi.danlimoshi;



/**
 *懒汉式（线程安全，同步方法）：
 * */
public class type4 {

    private static type4 type;

    private type4(){}


    private static synchronized type4 getInstance(){
        if(type == null){
            type = new type4();
        }
        return  type;
    }

}
