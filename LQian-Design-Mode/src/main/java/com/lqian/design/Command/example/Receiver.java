package com.lqian.design.Command.example;

/**
 * 接受者角色类
 */
public class Receiver {
    /**
     * 真正执行命令相应的操作
     */
    public void action(){
        System.out.println("执行操作");
    }
}
