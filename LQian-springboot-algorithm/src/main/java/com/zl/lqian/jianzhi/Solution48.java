package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des ${todo}
 */
public class Solution48 {

    /**
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     * @param num1
     * @param num2
     * @return
     * 两个二进制的相加结果是用一个异或门实现的；
     * 两个二进制的进位结果是用一个与门来实现的。并且左移一位
     */
    public int add(int num1,int num2) {
        int result, ans;
        do {
            result = num1 ^ num2;       // 每一位相加
            ans = (num1 & num2) << 1;   // 进位
            num1 = result;
            num2 = ans;
        } while (ans != 0);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(0&0);
    }
}
