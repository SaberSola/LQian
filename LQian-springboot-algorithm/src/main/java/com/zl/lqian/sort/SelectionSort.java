package com.zl.lqian.sort;


import java.util.Arrays;

/**
 * 选择排序
 *
 * 从待排序的数组中找到最小的的元素；
 * 如果最小的元素不是第一个将其和第一个元素互换位置
 * 从余下n-1个找出最小的 重复前边步骤
 *
 */
public class SelectionSort {


    /**
     * 选择排序
     *
     * 1. 从待排序序列中，找到关键字最小的元素；
     * 2. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
     * 3. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
     *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * @param arr  待排序数组
     */
    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            int min = i;
            for(int j = i+1; j < arr.length; j++){    //选出之后待排序中值最小的位置
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = arr[min];      //交换操作
                arr[min] = arr[i];
                arr[i] = temp;
                System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
    }

    public static void main(String[] args){
        int[] array = {1,4,2,5,6,3,8};
        selectionSort(array);

    }
}
