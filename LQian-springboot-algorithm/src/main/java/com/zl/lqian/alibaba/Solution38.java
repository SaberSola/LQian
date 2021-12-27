package com.zl.lqian.alibaba;

/**
 * @author zhanglei
 */
public class Solution38 {

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * f(n) = f(n-2) + f(n -1)
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 3) {
            return n;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++){
            int temp = second;
            second = (first+second)%1000000007;
            first = temp;
        }
        return second;
    }
}
