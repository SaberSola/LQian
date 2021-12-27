package com.zl.lqian.jianzhi.data;

import java.util.Arrays;

public class QuickSortBack {


    public static void sort(int[] a, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int index = part(a, start, end);
        sort(a, start, index - 1);
        sort(a, index + 1, end);
    }


    /**
     * @param a
     * @param start
     * @param end
     * @return
     */
    public static int part(int[] a, int start, int end) {
        int pivot = a[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < end && a[left] <= pivot) {
                left++;
            }
            while (right >= start && a[right] > pivot) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
            }
        }
        swap(a, left, right);
        return right;
    }

    public static void swap(int[] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,10,9,4,5,6};
        sort(a,0,a.length -1);
        Arrays.stream(a).forEach(System.out::println);
    }
}
