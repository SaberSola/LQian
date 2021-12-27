package com.zl.lqian.bytedance;

/**
 * @author zhanglei
 */
public class Solution4 {


    /**
     * 买卖股票的最佳时间
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 特殊判断
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[len][2];
        //dp[i][0] 代表第一天不持股 手里的现金
        //dp[i][1] 代表第一天持股,手里的现金
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        //从第二天开始
        for(int i = 1 ; i < prices.length; i ++){
            //今天没买股票,上一天没买入,或者上一天买了,今天卖了
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            //代表今天持股,上一天持股票,或者这一天刚买的股票
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

    /**
     * 最大子序列he
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i ++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}
