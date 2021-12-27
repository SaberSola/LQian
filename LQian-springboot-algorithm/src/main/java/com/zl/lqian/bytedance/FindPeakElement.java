package com.zl.lqian.bytedance;

public class FindPeakElement {

    /**
     * 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

}
