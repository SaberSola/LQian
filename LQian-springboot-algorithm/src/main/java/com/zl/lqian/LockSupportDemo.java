package com.zl.lqian;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start) <= 1000);//空转1s
            System.out.println("空转1s结束");
            LockSupport.park();//等待"许可"
            System.out.println(Thread.currentThread().getName() + "是否被中断："
                    + Thread.currentThread().isInterrupted());
        },"kira");
        thread.start();
        Thread.sleep(100000);
        thread.interrupt();//中断线程
    }
}
