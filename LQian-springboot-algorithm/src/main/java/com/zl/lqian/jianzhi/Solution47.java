package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des ${todo}
 */
public class Solution47 {

    /**
     * 求1+2+3+...+n，要求不能使用乘除法、
     * for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     *
     */
    public int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (n>0) && (sum += Sum_Solution(n-1)) > 0;
        return sum;
    }
}
