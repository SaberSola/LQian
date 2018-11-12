package com.zl.lqian.reflect;

/**
 *
 *  一个例子 说明 类锁和对象锁的区别
 */
public class TestLockDemo {

    private static int a = 0;
    public synchronized static void count(String flag){

        for (int i = 0; i < 1000; i++) {

            System.out.println(a ++ +" "+  flag);
        }

    }

    public static void main(String[] args){
        TestLockDemo demo = new TestLockDemo();
        TestLockDemo demo1 = new TestLockDemo();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.count("zl");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.count("qian");
            }
        });
        thread.start();
        thread1.start();
    }
}
