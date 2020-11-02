package com.zl.lqian.onehundred.slicewindow;

public class MaxSlidingWindow {


    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。返回滑动窗口中的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0){
            return new int[0];
        }
        int[] win = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++){
            //找到窗口的最大值
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j ++){
                max = Math.max(max, nums[j]);
            }
            win[i] = max;
        }
        return win;
    }
}
