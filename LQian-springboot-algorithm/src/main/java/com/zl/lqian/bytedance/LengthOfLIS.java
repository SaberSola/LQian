package com.zl.lqian.bytedance;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 */
public class LengthOfLIS {

    /**
     * 最长的递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        /**
         *
         * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
         *
         * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
         *
         *                            0   3    1    6    2    2    7
         *    定义双指针 i 和 j         推到动态 dp[i] = max(dp[i], dp[j] + 1)
         *     i = 0,j = 0            dp[i] = 1;
         *     i = 1,j = 0            dp[i] = 2  dp[j] = 1
         *     i = 2,j = 0            dp[i] = 2  dp[j] = 1
         *     i = 2 j = 1            dp[i] = 2  dp[j] = 1
         *
         *                                    0   3    1    6    2    2    7
         *                         i
         *                                    1   2    2    3    3    3    4    dp[i] = max(dp[i], dp[j] + 1)
         *
         *
         */
        int [] dp = new int[nums.length];
        //填充q
        Arrays.fill(dp,1);
        for(int i = 0; i < nums.length; i ++){
            for(int j = 0 ;j < i; j ++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }
        int res = 0;

        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
