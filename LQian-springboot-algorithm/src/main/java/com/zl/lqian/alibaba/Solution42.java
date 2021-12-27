package com.zl.lqian.alibaba;

/**
 * @author zhanglei
 */
public class Solution42 {


    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        /**
         * 双指针法
         */
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if ((nums[left] & 1) != 0) {
                left++;
                continue;
            }
            if ((nums[right] & 1) != 1) {
                right--;
                continue;
            }
            //开始交换位置
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }
}
