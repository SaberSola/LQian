package com.zl.lqian.onehundred.arrary;

import java.util.Arrays;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]

 输出: [4,9]
 */
public class MergeArrary {


    /**
     * 排序 + 双指针
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        //排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length, len2 = nums2.length;
        int[] ans = new int[Math.min(len1, len2)];
        int index1 = 0, index2 = 0, index = 0;
        //跳出循环
        while (index1 < len1 && index2 < len2) {
            if (nums1[index1] < nums2[index2]){
                index1++;
            }else if (nums1[index1] > nums2[index2]){
                index2++;
            }else {
                ans[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }

        }
        return Arrays.copyOfRange(ans, 0, index);

    }
}
