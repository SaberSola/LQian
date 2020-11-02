package com.zl.lqian.onehundred.sort;

import java.util.Arrays;

public class QuickSort {

    /**
     * 快速排序,找一个基准
     *
     * @param sourceArray
     * @return
     */
    public static int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return quickSort(arr, 0, arr.length - 1);
    }

    static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);

        }
        return arr;
    }

    /**
     * 分区方法
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    static int partition(int[] arr, int left, int right) {
        int pivot = left;
        //设置index
        int index = pivot + 1;
        for (int i = index; i < right; i ++){
            if (arr[i] < arr[pivot]){
                swap(arr,i,index);
                index++;
            }
        }
        swap(arr,pivot,index-1);
        return index-1;
    }


    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
