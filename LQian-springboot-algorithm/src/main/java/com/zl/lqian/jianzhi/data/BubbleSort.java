package com.zl.lqian.jianzhi.data;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-08
 * @Des ${todo}
 */
public class BubbleSort {


    /**
     * 与相邻的元素比较 小的提前
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++){
                if (arr[j-1] > arr[j]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int a [] = {1,5,4,6,2};
        BubbleSort.bubbleSort(a);
    }

}
