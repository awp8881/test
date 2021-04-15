package com.example.demo3.shejimoshi.danlimoshi;



/**
 *静态内部内
 *
 * */
public class type6 {

    private type6(){}

    private static class SingletonInstance{
        private static final type6 type = new type6();
    }

    public static type6 getInstance(){
        return  SingletonInstance.type;
    }
}
