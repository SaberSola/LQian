package com.zl.lqian.bytedance;

public class MaxProduct {

    /**
     *152. 乘积最大子数组
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        /**'给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
         * 动态规划 dp[i] 记录i的时候最带好纸
         * dp[i] = dp[i -1] * num[i],nums[i] 考虑负数,记录先前一个最大值,和最小
         *
         */
        int pre_max = nums[0], pre_min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur_max = Math.max(Math.max(pre_max * nums[i], pre_min * nums[i]), nums[i]);
            int cur_min = Math.min(Math.min(pre_max * nums[i], pre_min * nums[i]), nums[i]);
            ans = Math.max(ans, cur_max);
            pre_max = cur_max;
            pre_min = cur_min;
        }
        return ans;
    }

}
