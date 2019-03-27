package com.zl.lqian.alogrithm4.chapter2_1;

import com.zl.lqian.cs.algs4.StdOut;

/**
 * @Author zl
 * @Date 2019-03-16
 * @Des 插入排序
 */
public class Insertion {


    public static void sort(Comparable[] a) {

        int N = a.length;
        //将a[i] 和a[i] 之前的比较
        for (int i = 0; i < N; i++) {

            for (int j = i; j > 0; j--) {
                if (a[j].compareTo(a[j - 1]) < 0) {
                    //交换位置
                    Comparable t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }
            }
        }
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,6,3,17,19,4,5,90};
        sort(a);
        assert isSorted(a);
        show(a);
    }

}
