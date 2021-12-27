package com.zl.lqian.bytedance;

public class SearchRange {

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。

     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);
        return res;
    }

    /**
     * 二分查找,找到边界值
     *
     * @param nums
     * @param target
     * @param leftOrRight
     * @return
     */
    public int binarySearch(int[] nums, int target, boolean leftOrRight) {

        int res = -1;
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target >  nums[mid]) {
                left = mid + 1;
            } else {
                //找到第一个相等的值
                res = mid;
                if (leftOrRight) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return res;
    }
}
