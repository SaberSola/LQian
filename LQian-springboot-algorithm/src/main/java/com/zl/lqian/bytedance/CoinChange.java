package com.zl.lqian.bytedance;

import java.util.Arrays;

/**
 * 兑换硬币
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        /**
         * dp方程
         * dp[j] 填满容量为j的背包最少需要多少硬币
         * 初始化dp数组：因为硬币的数量一定不会超过amount，而amount <= 10^410
         * dp[i] = Math.min(dp[j],dp[j-coin] +1);
         * 当前填满容量j最少需要的硬币 = min( 之前填满容量j最少需要的硬币,
         * 填满容量 j - coin 需要的硬币 + 1个当前硬币）
         */
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        //coin 硬币的额度
        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount] != 10001 ? dp[amount] : -1;
    }
}
