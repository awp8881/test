package com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.order;

import com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.pizza.CheesePizza;
import com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.pizza.GreekPizza;
import com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.pizza.Pizza;

public class OrderPizza {

    public OrderPizza(){
        Pizza pizza  = null;
        String orderType;
        do{
            orderType= "greek";
            if(orderType.equals("greek")){
                pizza = new GreekPizza();
            }else if(orderType.equals("cheese")){
                pizza = new CheesePizza();
            }else {
                break;
            }
        }while (true);
    }


}
