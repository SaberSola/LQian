package com.zl.lqian;

import java.util.Objects;

public class helloword  {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits



    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    public static void main(String[] args) throws InterruptedException{

     /*  // int result = hashCode("zl","lei");
        int a = 5,b;
        //System.out.println(result & (12 -1));
        //a ++ 输出 5 表示 首次输出本身
        //++ a 输出6 表示第一次先 加上1
        System.out.println(a);

        System.out.println( (a = 8 ) <= 0 );*/

//     Thread thread = new Thread(new Runnable() {
//         @Override
//         public void run() {
//             try {
//                 Thread.sleep(3000);
//                 System.out.println("测试join");
//             }catch (InterruptedException e){
//
//             }
//         }
//     });
//
//        thread.start();
//       // thread.join();
//        System.out.println("主线程结束");

        int a,b;
        a = b = 9;
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
        int  [] d={};
        int x = 3;
        int y =4;

        System.out.println(a + b);
        boolean e=true?false:true==true?false:true;
        System.out.println(e);
    }

    public static int hashCode(String key,String value) {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }


}

class Car extends Vehicle
{
    public static void main (String[] args)
    {
        new  Vehicle(). run();
    }
    public void run()
    {
        System. out. println ("Car");
    }
}
class Vehicle
{
    public void run()
    {
        System. out. println("Vehicle");
    }
}


class demo extends Car{
    public static void main(String[] args){

        int x,y;

        x=5>>2;

        y=x>>>2;

        System.out.println(y);
     float f[] [] = new float[6][];
     float[] [] a = new float[6][];

        Integer i = 42;
        Long l = 42l;
        Double d = 42.0;


        /**
         * 包装类之间不可以用 == 比较的是地址 equals 比较的是内容
         */
        if(i.equals(42l)){} //注意 equals 首先会比较类型

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }

            final void demo(){}
        });
    }


}
class Example{
    String str=new String("tarena");

    public static void main(String args[]){
        char[]ch={'a','b','c'};
        Integer a  =10;
        Example ex=new Example();
        ex.change(ex.str,a);
        System.out.print(ex.str+" and ");
        System.out.print(a);
        Vehicle vehicle = new Car();
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new demo();

    }
    public void change(String str, int a ){
        //引用类型变量，传递的是地址，属于引用传递。
        str="test ok";
        a = 11;
    }
}