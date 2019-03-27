package com.zl.lqian.alogrithm4.chapter2_1;

import com.zl.lqian.cs.algs4.StdOut;
import com.zl.lqian.cs.algs4.In;

/**
 * @Author zl
 * @Date 2019-03-16
 * @Des 选择排序
 */
public class Selection {


    /**
     * 找出每次循环最小的 然后和第一个交换位置
     */

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);

        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; //临时便令
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,6,3,17,19,4,5,90};
        sort(a);
        assert isSorted(a);
        show(a);
    }

}
