package com.zl.lqian.onehundred.twofen;

public class MySqrt {

    /**
     * 计算并返回 x 的平方根，其中 x 是非负整数。由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                left = (int) (mid + 1);
            } else {
                right = (int) (mid - 1);
            }
        }
        return right;
    }
}
