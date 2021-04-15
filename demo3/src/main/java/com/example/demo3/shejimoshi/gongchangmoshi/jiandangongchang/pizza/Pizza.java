package com.example.demo3.shejimoshi.gongchangmoshi.jiandangongchang.pizza;

public abstract  class Pizza {

    private String name;


    public  abstract  void  prepare();

    public  void  bake(){
        System.out.println(name+"bake");

    }

    public void cut(){
        System.out.println(name+"cut");

    }

    public void box(){
        System.out.println(name+"box");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
