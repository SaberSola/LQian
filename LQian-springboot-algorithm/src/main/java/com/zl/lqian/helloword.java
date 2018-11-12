package com.zl.lqian;

import java.util.Objects;

public class helloword {

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
    }

    public static int hashCode(String key,String value) {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }
}
