package com.zl.lqian.bytedance;

/**
 * 123. 买卖股票的最佳时机 III
 */
public class MaxProfitDP {

    /**
     * 未持股，未卖出过股票：说明从未进行过买卖，利润为0
     * dp[i][0][0]=0
     * 未持股，卖出过1次股票：可能是今天卖出，也可能是之前卖的（昨天也未持股且卖出过）
     * dp[i][0][1]=max(dp[i-1][1][0]+prices[i],dp[i-1][0][1])
     * 未持股，卖出过2次股票:可能是今天卖出，也可能是之前卖的（昨天也未持股且卖出过）
     * dp[i][0][2]=max(dp[i-1][1][1]+prices[i],dp[i-1][0][2])
     * 持股，未卖出过股票：可能是今天买的，也可能是之前买的（昨天也持股）
     * dp[i][1][0]=max(dp[i-1][0][0]-prices[i],dp[i-1][1][0])
     * 持股，卖出过1次股票：可能是今天买的，也可能是之前买的（昨天也持股）
     * dp[i][1][1]=max(dp[i-1][0][1]-prices[i],dp[i-1][1][1])
     * 持股，卖出过2次股票：最多交易2次，这种情况不存在
     * dp[i][1][2]=float('-inf')
     *
     * @param prices
     * @return
     */
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int[][][] dp = new int[prices.length][2][3];
        int MIN_VALUE = Integer.MIN_VALUE / 2;//因为最小值再减去1就是最大值Integer.MIN_VALUE-1=Integer.MAX_VALUE
        //初始化
        dp[0][0][0] = 0;//第一天休息
        dp[0][0][1] = dp[0][1][1] = MIN_VALUE;//不可能
        dp[0][0][2] = dp[0][1][2] = MIN_VALUE;//不可能
        dp[0][1][0] = -prices[0];//买股票
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = MIN_VALUE;
        }
        return Math.max(0, Math.max(dp[prices.length - 1][0][1], dp[prices.length - 1][0][2]));
    }
}
