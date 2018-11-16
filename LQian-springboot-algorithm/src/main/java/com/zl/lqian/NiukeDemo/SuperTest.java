package com.zl.lqian.NiukeDemo;

import java.util.Date;


/**
 * 这里为什么不输出父类的名称
 */
public class SuperTest extends Date {

    private static final long serialVersionUID = 1L;
    private void test(){
        System.out.println(super.getClass().getName());
    }

    public static void main(String[]args){
        new SuperTest().test();
    }

    public static final void a(){}
}
