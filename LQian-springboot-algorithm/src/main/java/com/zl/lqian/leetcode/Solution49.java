package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-16
 * @Des ${todo}
 */
public class Solution49 {

    /**
     * 买卖股票的最佳时间
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxp = 0;
        int low = Integer.MAX_VALUE;
        for(int p : prices) {
            if(p < low) {
                low = p;
            }
            maxp = Math.max(maxp, p - low);
        }

        return maxp;

    }

    /**
     * 所有上涨的都买卖,跌的永远不买
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }
        return profit;

    }
}
