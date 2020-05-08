package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-08
 * @Des ${todo}
 */
public class Solution23 {


    /**
     * 长度为N的无序数组在保证“无重复，无越界”的情况下，最多能装下N以内的正整数。
     *
     * 借用数组的[0, n-1]存储区间，试图有序的摆放[1, N]区间的正整数：
     * nums[i] 存储正整数 i + 1
     *
     * 如果出现重复，越界的情况，说明无效数字多占了一个坑位，丢掉无效数字的同时坑位减一。
     *
     * 将数组换成hash表的思想
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length -1;
        for (int i = 0; i < len ; i++){
            while (nums[i] >= 1 && nums[i] < len && nums[nums[i] - 1] != nums[i]){
                //需要交换位置
                swap(nums,i,nums[i] -1);
            }
        }
        for (int i = 0; i < len ; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return len + 1;
    }

    public void swap(int nums[],int index1,int index2){
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}
