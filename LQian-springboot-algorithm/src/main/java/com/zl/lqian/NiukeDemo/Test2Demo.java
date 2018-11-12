package com.zl.lqian.NiukeDemo;

public class Test2Demo {

    public static void main(String args[]) {
        Thread t=new Thread(){
            public void  run(){
                dianping();

            }
        };
        t.run();
        System.out.println("dazhong");
    }
    static void dianping(){
        try {
            Thread.sleep(2000);
            System.out.println("dianping");
        }catch (Exception e){

        }

    }

    public void print(){}
}
