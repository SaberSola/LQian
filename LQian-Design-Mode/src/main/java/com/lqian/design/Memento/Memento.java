package com.lqian.design.Memento;

import java.util.ArrayList;
import java.util.List;

public class Memento  {

    int money;        //获取的金钱
    ArrayList fruits; //获取的水果

    public int getMoney(){ //得到获取的金钱
        return money;
    }

    Memento(int money) {  // 构造函数(wide interface)
        this.money = money;
        this.fruits = new ArrayList();
    }

    void addFruit(String fruit) { // 添加水果(wide interface)
        fruits.add(fruit);
    }

    List getFruits(){   //获取所有水果
        return fruits;
    }
}
