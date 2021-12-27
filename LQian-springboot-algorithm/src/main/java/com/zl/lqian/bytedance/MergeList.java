package com.zl.lqian.bytedance;

public class MergeList {

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //合并两个有序数组
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);
        //双指针
        int p1 = 0;
        int p2 = 0;
        //p是nums1的指针
        int p = 0;
        while (p1 < m & p2 < n) {
            if (nums1_copy[p1] < nums2[p2]) {
                nums1[p] = nums1_copy[p1];
                p1++;
            } else {
                nums1[p] = nums2[p2];
                p2++;
            }
            p++;
        }
        //判断是否有剩余没有比较
        if (p1 < m){
            //说用nums1——copy还有剩余
            System.arraycopy(nums1_copy,p,nums1,p,m-p1);
        }else {
            System.arraycopy(nums2, p2, nums1, p, n - p2);
        }
    }

}
