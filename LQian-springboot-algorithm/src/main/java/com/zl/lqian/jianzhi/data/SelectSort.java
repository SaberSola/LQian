package com.zl.lqian.jianzhi.data;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-08
 * @Des 选择排序
 */
public class SelectSort {

    /**
     * 找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置(如果第一个元素就是最小元素那么它就和自己交换)。
     * 其次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。这种方法我们称之为选择排序
     *
     * @param a
     * @return
     */
    public static void selectSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[min] < a[j]) {
                    min = j;
                }
            }
            int temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
        Arrays.stream(a).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int a [] = {1,5,4,6,2};
        SelectSort.selectSort(a);
    }

}
