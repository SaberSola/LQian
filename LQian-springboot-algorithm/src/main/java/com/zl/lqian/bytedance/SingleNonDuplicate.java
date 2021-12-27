package com.zl.lqian.bytedance;

public class SingleNonDuplicate {

    /**
     * 540. 有序数组中的单一元素
     * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * <p>
     * 请你找出并返回只出现一次的那个数。
     * <p>
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,1,2,3,3,4,4,8,8]
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: nums =  [3,3,7,7,10,11,11]
     * 输出: 10
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {

        /**
         * 观察题目所给数组[1,1,2,3,3,4,4,8,8]，可以得出以下三个结论来满足二段性：
         *
         * 目标数必处于偶数下标
         * (目标数的左部的偶下标元素)必定等于其后一个数
         * 目标数 和(目标数的右部的偶下标元素)必定不等于其后一个数
         *
         */
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (mid % 2 != 0) mid--; //调整mid为偶下标元素
            if (nums[mid] == nums[mid + 1]) {
                l = mid + 2;
            } //等于后一个数，说明mid处于目标数的左部
            else {
                r = mid;
            } //不等于后一个数，说明mid为目标数或处于目标数的右部
        }
        return nums[l];
    }
}