package com.zl.lqian.alogrithm4.chapter2_1;

import com.zl.lqian.cs.algs4.StdOut;

/**
 * @Author zl
 * @Date 2019-03-16
 * @Des 希尔排序
 */
public class Shell {

    /**
     *
     *shell 排序
     * 使数组任意
     *
     */
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        //拆为h个小数组
        while (h < N /3){
            h = 3 * h + 1;
        }
        while (h >= 1){
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }




    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
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
}
