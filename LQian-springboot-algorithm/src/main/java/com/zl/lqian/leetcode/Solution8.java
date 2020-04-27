package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-27
 * @Des 回文数
 *
 * 121
 * 正 121
 * 倒 121
 * 就是回文数
 */
public class Solution8 {


    /**
     * 判断回文数
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {

        //计算倒叙值 比较大小
        if (x < 0) return false;
        int cur = 0;
        int num = x;
        while (num != 0){
            cur = cur * 10 + num % 10;
            num = num/10;
        }
        return cur == x;
    }
}
