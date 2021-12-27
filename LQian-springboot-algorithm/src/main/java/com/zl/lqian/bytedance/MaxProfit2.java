package com.zl.lqian.bytedance;

public class MaxProfit2 {

    /**
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /**
         * 思路还是动态规划
         * dp[i][0] 第i天的时候手机么有股票的最大利润
         * dp[i][1] 第i天的时候,手里持有股票的最大利润
         *
         * dp[i][0] 当天不持有 前一天不持有 dp[i-1][0] 和前一天持有,今天卖出 dp[i-1][1] + prices[i];的最大值
         * dp[i][1] 当天持有股票 前一天不持有，今天买入 dp[i-1][0] -  prices[i]  前天持有,今天也持有 dp[i-1][1] 最大值
         */
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[len - 1][0];
    }

    /**
     * 多数元素
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int countNum = nums[0];
        int count = 1;
        for (int i= 1; i< nums.length; i ++){
            if (countNum == nums[i]){
                count = count +1;
            }else {
                count =count -1;
                if (count ==0){
                    countNum = nums[i];
                    count = 1;
                }
            }
        }
        return countNum;
    }
}
