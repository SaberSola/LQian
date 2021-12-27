package com.zl.lqian.bytedance;

public class MySqrt {


    /**
     * 实现int sqrt(int x)函数。
     * <p>
     * 计算并返回X的平方根，其中x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        //思路是二分查找
        int l = 0, r = x, ans = -1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
