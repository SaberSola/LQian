package com.zl.lqian.onehundred.dp;

public class LengthOfLIS {

    /**
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     *
     * @param num
     * @return
     */
    public int lengthOfLIS(int[] num) {
        if (num.length < 1) return 0;
        int[] dp = new int[num.length];
        int result = 1;
        for (int i = 0; i < num.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (num[i] < num[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
