package com.zl.lqian.bytedance;

import java.util.Arrays;

public class MaxProfit3 {

    /**
     * 买股票,但是只能交易K次
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        //一天买,一天卖,所以的最大的交易次数只能是天数的一半
        k = Math.min(k, n / 2);
        /**
         * buy[i][j] 表示在第i天进行第j次交易, 并且手上恰好持有股票的最大利润
         * sell[i][j] 表示在第i天已经交易了j次,并且手上不持有股票的最大利益
         *
         * 第i天持有股票
         * buy[i][j]  = Math.max(昨天买了,昨天卖了-今天买)
         * 第i天不持有股票
         * sell[i][j] = Math.max(昨天不持有股票,昨天卖了+)
         */
        int buy[][] = new int[n][k + 1];
        int sell[][] = new int[n][k + 1];
        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        //填充第一天
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }
}
