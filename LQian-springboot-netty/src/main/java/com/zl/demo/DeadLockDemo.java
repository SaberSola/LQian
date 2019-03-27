package com.zl.demo;

/**
 * @Author zl
 * @Date 2019-03-13
 * @Des ${todo}
 */
public class DeadLockDemo implements Runnable {

   public int flag = 1;

   private static Object o1 = new Object();
   private static Object o2 =  new Object();


    public static String str1 = "str1";
    public static String str2 = "str2";

    @Override
    public void run() {
        System.out.println("falg = " + flag);
        if (flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            synchronized (o2){
                System.out.println(1);
            }

        }
        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            synchronized (o1){
                System.out.println(0);
            }

        }
    }

    public static void main(String[] args){
      /*  DeadLockDemo deadLockDemo = new DeadLockDemo();
        DeadLockDemo deadLockDemo1 = new DeadLockDemo();
        deadLockDemo.flag = 1;
        deadLockDemo1.flag = 0;
        for (int i = 0; i < 10; i++) {
            new Thread(deadLockDemo).start();
            new Thread(deadLockDemo1).start();
        }*/
        Thread a = new Thread(() -> {
            try{
                while(true){
                    synchronized(DeadLockDemo.str1){
                        System.out.println(Thread.currentThread().getName()+"锁住 str1");
                        Thread.sleep(1000);
                        synchronized(DeadLockDemo.str2){
                            System.out.println(Thread.currentThread().getName()+"锁住 str2");
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        });

        Thread b = new Thread(() -> {
            try{
                while(true){
                    synchronized(DeadLockDemo.str2){
                        System.out.println(Thread.currentThread().getName()+"锁住 str2");
                        Thread.sleep(1000);
                        synchronized(DeadLockDemo.str1){
                            System.out.println(Thread.currentThread().getName()+"锁住 str1");
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        });

        a.start();
        b.start();
    }
}
