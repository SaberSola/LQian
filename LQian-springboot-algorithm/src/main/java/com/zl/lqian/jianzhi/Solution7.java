package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-05
 * @Des ${todo}
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 * F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)
 */
public class Solution7 {


    public static Integer fn(Integer n){

        if (n <= 1){
            return 1;
        }
        return fn(n-1) + fn(n-2);
    }

    public int Fibonacci(int n) {
        int pre1 = 1;
        int pre2 = 0;
        int cur = 0;
        for(int i = 2; i <= n; i++){
            cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        if(n < 2){
            return n;
        }
        return cur;
    }


}
