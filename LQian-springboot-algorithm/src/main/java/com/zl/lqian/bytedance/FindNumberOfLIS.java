package com.zl.lqian.bytedance;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindNumberOfLIS {


    public int findNumberOfLIS(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        //确定dp数组 dp[i] 就代表从0到i下标的数组递增序列的长度
        int[] dp = new int[nums.length];

        //count[i] 代表以nums[i]结尾的字符串,最长递增子序列个数为 count[i];
        int[] counts = new int[nums.length];

        Arrays.fill(dp, 1);
        Arrays.fill(counts, 1);

        int max = 1;
        int result =0;
        for (int i = 1; i < nums.length; i ++){
            for (int j = 0 ; j < i; j++){
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        counts[i] = counts[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }
        for (int i = 0; i < nums.length; i++)
            if (dp[i] == max) result += counts[i];
        return result;
    }
}
