package com.zl.lqian;

public class Threadinterrupt {

    public static void  main(String[] args){

        InnerClass innerClass = new InnerClass();
        Thread thread = new Thread(innerClass);
        thread.start();
        long i = System.currentTimeMillis();
        while (System.currentTimeMillis() - i < 10 * 1000) {
            thread.isAlive();
        }
        thread.interrupt();


    }
    static class InnerClass implements Runnable {

        @Override
        public void run() {
            System.err.println("start work");
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println("doing work");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace(); //线程处于中断时会跑遗产 但是在异常中一定要再次中断 否则会有死循环
                    Thread.currentThread().interrupt();

                }
            }
            System.err.println("done work");
        }
    }

}
