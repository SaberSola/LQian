package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des ${todo}
 */
public class Solution11 {

    public static void main(String[] args) {
       count(10);
    }

    public static Integer count(int n){
        int count=0;
        while(n!=0){
            count++;
            n=n&(n-1);
        }
        return count;
    }

    /**
     *       101
     *       100
     *
     *       100
     *       011
     *
     *       000
     *
     *
     *
     *
     */


}
