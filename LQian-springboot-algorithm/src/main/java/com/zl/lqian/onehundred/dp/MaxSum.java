package com.zl.lqian.onehundred.dp;

public class MaxSum {

    /**
     * 给定一个整数数组 nums ，
     * 找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * @param A
     * @return
     */
    public static int maxSubArray_DP(int[] A) {

        /**
         * dp的动态方程 dp[i] = Max(dp[i-1] + num[i],num[i])
         */

        int[] dp = new int[A.length];
        dp[0] = A[0];
        int max = dp[0];
        for (int i = 1; i < A.length; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(dp[i],max);
        }

        return max;

    }
}
