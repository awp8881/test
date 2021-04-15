package com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.simpleFactory;


import com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.pizza.CheesePizza;
import com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.pizza.GreekPizza;
import com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.pizza.Pizza;

/**
 * 简单工厂类
 */
public class SimpleFactory {

    public Pizza creatPizza(String type){
        if(type.equals("greek")){
            return new GreekPizza();
        }else if(type.equals("cheese")){
            return new CheesePizza();
        }else {
            return null;
        }
    }
}
