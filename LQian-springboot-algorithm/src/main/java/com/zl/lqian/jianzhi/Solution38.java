package com.zl.lqian.jianzhi;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-10
 * @Des 统计一个数字在排序数组中出现的次数。
 */
public class Solution38 {

    /**
     * 二分查找 统计左右
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array, int k) {

        int index = check(array, k);
        if (index < 0) {
            return 0;
        }
        int left = index - 1;
        int right = index + 1;
        int times = 1;
        while (left >= 0 && array[left--] == k) {
            times++;
        }
        while (right < array.length && array[right++] == k) {
            times++;
        }
        return times;
        // System.out.println(times);

    }

    private int check(int[] array, int k) {
        if (array.length == 0) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        if (k < array[low] || k > array[high] || low > high) {
            return -1;
        }

        while (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] > k) {
                high = mid - 1;
            } else if (array[mid] < k) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }




    public int GetNumberOfK1(int[] array, int k) {
        int index = find(array, k);
        if (index < 0) {
            return 0;
        }
        int left = index -1;
        int right = index + 1;
        int tims = 1;
        while (left >=0 && array[left --] == k){
            tims++;
        }
        while (right <array.length && array[right++] == k ){
            tims++;
        }

        return tims;


    }

    public int find(int[] array, int k) {
        if (array.length ==0){
            return -1;
        }
        int low = 0;
        int high = array.length -1;
        int mid = 0;
        if (k < array[low] || k > array[high] || low > high) {
            return -1;
        }
        while (low <= high){
            mid = (low + high)/2;
            if (array[mid] > k){
                high = mid - 1;
            }else if (array[mid] < k){
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }




}
