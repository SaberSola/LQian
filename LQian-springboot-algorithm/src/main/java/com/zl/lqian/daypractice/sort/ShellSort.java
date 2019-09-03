package com.zl.lqian.daypractice.sort;

import com.zl.lqian.daypractice.Example;

/**
 * @Author zl
 * @Date 2019-09-02
 * @Des ${todo}
 */
public class ShellSort extends Example {


    /**
     * 希尔排序(基于插入排序的快速排序)
     * 1: 使数组任意间隔为h个的都是有序的
     * 2: 使用h个有序数组合并
     *
     * @param a
     */
    @Override
    protected void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        //分割数组
        while (h < n / 3) {
            h = 3 * h + 1;      //4，13，40
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                //将a[i]和 插入到a[i-h] a[i-2*h] a[i-3*h]中
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        Integer a[] = {0,3, 11, 5, 6, 4, 1, 9, 5};
        shellSort.sort(a);
        shellSort.show(a);
    }
}
