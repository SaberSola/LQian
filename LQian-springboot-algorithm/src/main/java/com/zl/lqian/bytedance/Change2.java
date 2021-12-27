package com.zl.lqian.bytedance;

public class Change2 {


    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * <p>
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * <p>
     * 假设每一种面额的硬币有无限个。 
     * <p>
     * 题目数据保证结果符合 32 位带符号整数。
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        /**
         * 输入：amount = 5, coins = [1, 2, 5]
         * 输出：4
         * 解释：有四种方式可以凑成总金额：
         * 5=5
         * 5=2+2+1
         * 5=2+1+1+1
         * 5=1+1+1+1+1
         * dp[i]: 标识金额为i的组合数
         * 初始化dp[0] = 1
         * 遍历 i从cron到amount 将dp[i-corn] 加到dp[i];
         * 举个例子
         *           0   1   2    3   4   5   amount
         *    1      1   1   1    1    1   1
         *
         *    2      1   1   2    2    3   3
         *
         *    5      1   1   2    2    3   4
         *
         *    dp[2] = dp[2] + dp[i -coin]
         *
         *
         */

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int corn : coins) {
            for (int i = corn; i <= amount; i++) {
                dp[i] = dp[i] + dp[i - corn];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int num [] = new int[]{2,5,7};
        change(14,num);

    }
}
