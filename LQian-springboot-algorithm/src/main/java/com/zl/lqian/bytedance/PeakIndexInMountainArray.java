package com.zl.lqian.bytedance;

/**
 * 852. 山脉数组的峰顶索引
 * 数组最大值的小标
 */
public class PeakIndexInMountainArray {


    /**
     * 暴力法
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int ans = -1;
        for (int i = 1; i < n - 1; ++i) {
            if (arr[i] > arr[i + 1]) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
