package com.zl.lqian.jianzhi.data;

/**
 * @Author zl
 * @Date 2020-04-10
 * @Des ${todo}
 */
public class QuickSort {


    /**
     * 从数列中挑出一个元素，称为 “基准”（pivot）；
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * @param a
     *
     * 1，3，7，5，6，4
     */


    public static void sort(int[]a,int start,int end) {
        if (end - start < 1){
            return;
        }
        int index = part(a,start,end);
        sort(a,start,index -1);
        sort(a,index + 1,end);
    }

    public static int part(int[] a,int start,int end) {
        int pivot = a[start];
        int left = start;
        int right = end;
        while (left < right){
            while (left < end && a[left] <= pivot) left ++;
            while (right>= start && a[right] > pivot) right--;
            if (left < right) {
                swap(a, left, right);
            }
        }
        swap(a,left,right);
        return right;
    }


    public static void swap(int[]a,int left,int right){
        int temp = a[left];
        a[left] =a[right];
        a[right] = temp;
    }
}
