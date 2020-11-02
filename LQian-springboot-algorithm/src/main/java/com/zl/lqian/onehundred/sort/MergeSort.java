package com.zl.lqian.onehundred.sort;

import java.util.Arrays;

public class MergeSort {

    /**
     * 归并排序
     *
     * @param
     * @return
     */
    public static int[] sort(int[] sourceArray) {
        //分组
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(sort(left), sort(right));
    }

    //排序
    static int[] merge(int[] left, int[] right) {
        //排序后的数组
        int[] result = new int[left.length + right.length];
        //
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            //排序
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return result;
    }


    /**
     * 分而治之思想
     */

    public static void sort2(int[] arr) {
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sortMerge(arr,0,arr.length-1,temp);
    }

    static void sortMerge(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sortMerge(arr, left, mid, temp);//左边归并排序变的有序
            sortMerge(arr, mid+1, right, temp);
            //合并两个有序的子序列
            mergeSort(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并两个有序的子序列
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    static void mergeSort(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0; //临时数组指针
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        //
        while (i <= mid){
            temp[t++] = arr[i++];
        }
        while (j<= right){
            temp[t++] = arr[j++];
        }
        t=0;
        //将temp中的元素
        while (left <= right){
            arr[left++] = temp[t++];
        }

    }

    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort2(arr);
        System.out.println(Arrays.toString(arr));
    }


}
