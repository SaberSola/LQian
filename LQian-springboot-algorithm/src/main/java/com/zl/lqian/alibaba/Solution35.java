package com.zl.lqian.alibaba;

public class Solution35 {

    /**
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int mid = 0;
        int right = nums.length - 1;
        while (left <= right) {
            mid = left + (right - left ) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 斐波那契梳理
     * F(n) = F(n-1) + F(n-2)
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n < 2){
            return n;
        }
        return fib(n-1) + fib(n-2);
    }

    /**
     * 非递归解法
     * @param n
     * @return
     */
    public int fib2(int n) {
        int first = 0;
        int second = 1;
        while (n-- > 0) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return first;
    }

}
