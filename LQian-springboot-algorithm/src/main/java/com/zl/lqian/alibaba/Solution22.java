package com.zl.lqian.alibaba;

public class Solution22 {

    /**
     * 爬楼梯
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /**
         * dp[n] = dp[n-1] + dp[n-2]
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <=n ; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
