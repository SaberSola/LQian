package com.zl.lqian.bytedance;

public class ClimbStairs {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /**
         * 也就是说爬到n有两种 一种是 一步到,第二种是两步到
         * 所以dp[i] = dp[i-1] + dp[i-2]
         */

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
