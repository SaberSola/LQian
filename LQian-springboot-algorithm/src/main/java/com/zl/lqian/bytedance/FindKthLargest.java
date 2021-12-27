package com.zl.lqian.bytedance;

import java.util.Random;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class FindKthLargest {


    static Random random = new Random();

    /**
     * 核心思想就是快速排序,
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     * 快速排序
     *
     * @param nums
     * @param l
     * @param r
     * @param index
     * @return
     */
    static int quickSelect(int nums[], int l, int r, int index) {
        /**
         * 找到基准点
         */
        int pivot = privot(nums, l, r);
        if (pivot == index) {
            return nums[pivot];
        } else {
            return pivot < index ? quickSelect(nums, pivot + 1, r, index) : quickSelect(nums, l, pivot - 1, index);
        }
    }

    static int privot(int[] nums, int l, int r) {
        //找到基准点
        int i = random.nextInt(r - l + 1) + l;
        //pivot pivot放在最右边
        swap(nums, i, r);
        //开始排序 比nums[i] 小的都排在左边,比nums[i] 大的都放在右边
        return partion(nums, l, r);
    }

    static int partion(int nums[], int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) ;
            i = i + 1;
            swap(nums, i, j);
        }
        swap(nums, i + 1, r);
        return i + 1;
    }


    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
