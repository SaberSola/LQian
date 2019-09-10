package com.zl.lqian;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptDemo {

    private volatile static  boolean stop = false;

   public static LinkedBlockingQueue queue = new LinkedBlockingQueue();

    static class MyRunnable implements Runnable{

        public void run() {
            queue.add(1);
            while(!Thread.currentThread().isInterrupted()){
                try {
                    System.out.println(Thread.currentThread().isInterrupted());
                    queue.take();
                    Thread.sleep(1000);
                    System.out.println("sleep begin!");
                    System.out.println("sleep end!");
                } catch (Exception e) {
                    System.out.println("睡眠中遇中断进入catch，重置中断标志位，退出循环！");
                    Thread.currentThread().interrupt();
                }finally {
                    System.out.println("zgablei");
                }

            }
            System.out.println("中断后正常退出");


        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(new MyRunnable());

        t.start();

        //让线程t运行一段时间
        Thread.sleep(3000);
        stop = true;
        t.interrupt();
        t.join();
        System.out.println("程序退出");

    }


}
