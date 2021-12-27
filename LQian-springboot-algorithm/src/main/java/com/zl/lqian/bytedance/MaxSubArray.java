package com.zl.lqian.bytedance;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaxSubArray {

    /**
     * nums = [-2,1,-3,4,-1,2,1,-5,4]
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //确定好动态方程
        //dp[i] = Max(dp[i-1] + nums[i],nums[i])
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}
