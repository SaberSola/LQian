package com.zl.lqian.waitornotify;

/**
 * @Author zl
 * @Date 2019-05-17
 * @Des ${todo}
 */
public class WaitNotifyDemo {

    private static final WaitNotifyObject object1 = new WaitNotifyObject();

    private static final WaitNotifyObject object2 = new WaitNotifyObject();


    public static void main(String [] args){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread 开始 wait");
                    object1.await();
                    System.out.println("thread 已经被唤醒");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 开始 wait");
                object1.await();
                System.out.println("thread1 已经被唤醒");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 开始 wait");
                object2.await();
                System.out.println("thread2 已经被唤醒");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread3 开始 wait");
                object2.await();
                System.out.println("thread3 已经被唤醒");
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    object2.wakeUpAll();
                    System.out.println("thread4 执行唤醒操作");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
