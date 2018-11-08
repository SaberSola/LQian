package com.zl.lqian;

public class Threadinterrupt2 {

    public static void main (String[] args){


        InnerClass innerClass = new InnerClass();
        Thread thread = new Thread(innerClass);
        thread.start();
        long i = System.currentTimeMillis();
        while (System.currentTimeMillis() - i < 10 * 1000) {
            thread.isAlive();
        }
        thread.interrupt();  //中断只是个标志位 并不是立刻停止线程

    }


    static class InnerClass implements Runnable {

        @Override
        public void run() {
            System.err.println("start work");
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("doing work");
            }
            System.err.println("done work");
        }
    }

}
