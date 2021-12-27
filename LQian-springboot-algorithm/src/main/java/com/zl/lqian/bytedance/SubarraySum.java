package com.zl.lqian.bytedance;

public class SubarraySum {

    /**
     * 560. 和为 K 的子数组
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,1], k = 2
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3], k = 3
     * 输出：2
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
