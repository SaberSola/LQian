package com.zl.lqian.bytedance;

public class NextPermutation {

    /**
     * 下一个排列
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        /**
         *  字符  9 8 6 9 4 6 5 3
         *  目标  9 8 6 9 5 3 4 6
         *
         *  第一步 倒叙查找找到第一个  nums[i-1] < num[i] 的数字 4
         *  第二步 倒叙查找 找到第一个 num[j] > num[i - 1] 的数字 5
         *
         *  交换 j 和 i - 1的 位置 9 8 6 9 5 6 4 3
         *  //逆序排序 i之后位置    9 8 6 9 5 3 4 6
         */
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
