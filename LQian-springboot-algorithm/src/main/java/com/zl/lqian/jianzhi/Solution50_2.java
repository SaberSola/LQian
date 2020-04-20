package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-17
 * @Des ${todo}
 */
public class Solution50_2 {


    /**
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]A[1]...A[i-1]A[i+1]...A[n-1]。不能使用除法。
     */
    public int[] multiply(int[] A) {


        int[] B = new int[A.length];
        int temp = 0;
        for (int i = 0; i < A.length; i++) {
            temp = A[i];
            A[i] = 1;
            B[i] = 1;
            for (int j = 0; j < A.length; j++) {
                B[i] *= A[j];
            }
            A[i] = temp;
        }
        return B;
    }
}
