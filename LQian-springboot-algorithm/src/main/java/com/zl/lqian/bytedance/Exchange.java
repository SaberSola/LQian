package com.zl.lqian.bytedance;

public class Exchange {

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        //双指针发
        int left = 0;
        int right = nums.length - 1;
        //开始碰头
        while (left < right) {
            if (nums[left] / 2 != 0) {
                left++;
                continue;
            }
            if (nums[right] / 2 != 1) {
                right++;
                continue;
            }
            //开始交换
            //开始交换位置
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }
}
