package com.zl.lqian.alibaba;

import java.util.Arrays;

/**
 * 349. 两个数组的交集
 */
public class Solution32 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0;
        int index1 = 1;
        int index2 = 0;
        //循环到边界
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                //保证数据的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * 两个数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        //新的数据,长度为最小的那个
        int length1 = nums1.length, length2 = nums2.length;
        int[] result = new int[Math.min(length1,length2)];
        //
        int index = 0,index1 = 0,index2 = 0;
        //当某个数组
        while (index1 < length1 && index2 < length2){
            if (nums1[index1] < nums2[index2]){
                index1++;
            }else if (nums1[index1] > nums2[index2]){
                index2++;
            }else {
                //数字相同
                //保证数字唯一性
                if (index == 0 ||nums1[index1] != result[index -1]) {
                    result[index] = nums1[index1];
                    index++;
                }
                index1++;
                index2++;
            }
        }
        return Arrays.copyOfRange(result,0,index);
    }

    /**
     * s数组交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        //新的数据,长度为最小的那个
        int length1 = nums1.length, length2 = nums2.length;
        int[] result = new int[Math.min(length1,length2)];
        int index = 0,index1 = 0,index2 = 0;
        while (index1 < length1 && index2 < length2){
            if(nums1[index1] < nums2[index2]){
                index1++;
            }else if (nums1[index1] > nums2[index2]){
                index2++;
            }else {
                result[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(result,0,index);
    }
}
