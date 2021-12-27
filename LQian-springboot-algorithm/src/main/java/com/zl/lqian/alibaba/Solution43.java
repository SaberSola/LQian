package com.zl.lqian.alibaba;

/**
 * @author zhanglei
 */
public class Solution43 {

    /**
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //动态规划,
        /**
         * nums   -2  1  -3  4  -1   2   1  -5  4
         * dp     -2  1  -2  4   3   5   6   1  5
         *
         * dp[i] = dp[i-1] + nums[i] / nums[i]
         */
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}
