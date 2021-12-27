package com.zl.lqian.bytedance;

import java.util.HashSet;

public class LongestConsecutive {

    /**
     * 128. 最长连续序列 最长的连续子序列
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            //保证和前边不是连续的
            if (!set.contains(num - 1)) {
                int count = 0;
                while (set.contains(num)) {
                    count++;
                    num++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}
