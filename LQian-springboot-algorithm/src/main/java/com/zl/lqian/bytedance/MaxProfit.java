package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;

public class MaxProfit {

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * <p>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        /**
         * dp[n][0] 当天不要持股,手里的现金
         * dp[n][1] 当天持股,手里的现金
         */
        dp[0][0] = 0;
        //持股需要减去当天的股价
        dp[0][1] = -prices[0];
        for (int n = 1; n < prices.length; n++) {
            //第N天不持股,
            dp[n][0] = Math.max(dp[n - 1][0], dp[n - 1][1] + prices[n]);
            //第n天持股 判断昨天也持股 注意股票只可以持有一次
            dp[n][1] = Math.max(dp[n - 1][1], -prices[n]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])){
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i],i);
        }
        return new int[0];
    }
}
