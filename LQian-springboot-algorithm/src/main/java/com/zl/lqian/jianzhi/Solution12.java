package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des ${todo}
 *
 *
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Solution12 {

    public double Power(double base, int exponent) {
        double ans = 1.0;
        if (exponent > 0){
            for (int i = 1 ; i <= exponent; i ++){
                ans = ans * base;
            }
        }else {
            for (int i = 1 ; i <= -exponent; i ++){
                ans = ans * base;
            }
            ans = 1/ans;
        }
        return ans;
    }


}
