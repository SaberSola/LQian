package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-27
 * @Des 非旋转数组的最小
 * 6, 7, 9, 1, 3, 5, 5
 * 找到最小 1的下摆哦
 */
public class MinNumber {


    /**
     * 解法思路
     * 二分法 寻找变化点
     * @param array
     * @return
     */
    public static int minInReversingList(int[] array) {

        if (array == null || array.length == 0) {
            return -1;
        }
        if (array.length == 1 || array[array.length - 1] > array[0]) {
            return array[0];
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {

            int mid = left + (right - left) / 2;
            if (array[mid] > array[mid + 1]) {
                return array[mid + 1];
            }
            if (array[mid - 1] > array[mid]) {
                return array[mid];
            }
            if (array[mid] > array[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            return -1;
        }

        return 0;
    }

    /**
     * 若非递减排序数组中有重复元素，求最小元素
     * 时间复杂度：O(log n)，空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] array = {6, 7, 9, 1, 3, 5, 5};
        System.out.println("旋转数组的最小元素：" + MinNumber.minInReversingList(array));
    }

}
