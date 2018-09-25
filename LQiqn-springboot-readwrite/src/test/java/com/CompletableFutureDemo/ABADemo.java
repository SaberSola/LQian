package com.CompletableFutureDemo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    private static AtomicInteger atomicInt = new AtomicInteger(100);

    private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(atomicInt.get(), 0);


    public static void main(String[] args) throws InterruptedException{

        /*Thread intT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInt.compareAndSet(100,101);
                atomicInt.compareAndSet(101,100);
            }
        });

        Thread inT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1);
                }catch (InterruptedException e){e.printStackTrace();}
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println(c3);
            }
        });
        intT1.start();
        inT2.start();
        intT1.join();
        inT2.join();
*/
        Thread refT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1);
                }catch (InterruptedException e){e.printStackTrace();}

                atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                System.out.println("第一行"+atomicStampedRef.getStamp());
                atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                System.out.println("第二行"+atomicStampedRef.getStamp());
            }
        });

        Thread refT2 = new Thread(new Runnable() {
            int stamp = atomicStampedRef.getStamp();
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){e.printStackTrace();}
                System.out.println("第三行"+atomicStampedRef.getStamp());
                boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println("第四行"+atomicStampedRef.getStamp());
                System.out.println(c3);
            }
        });
        refT1.start();
        refT2.start();
    }
}
