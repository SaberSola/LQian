package com.zl.lqian.daypractice;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2019-09-01
 * @Des ${todo}
 */
public abstract class Example {


    protected abstract void sort(Comparable[] a);

    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * @param a
     * @param lo  低位
     * @param mid 中值
     * @param hi  高位
     */
    protected void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo,mid],a[mid+1,hi] 归并 合并为一个有序的数字
        int i = lo, j = mid + 1;
        Comparable[] aux = null;
        for (int k = lo; k <= hi; k++) { //先把数组a复制到数组aux
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {            // i 处于右半部分
                a[k] = aux[j++];
            } else if (j > hi) {      //  j
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    protected void show(Comparable[] a) {
        Arrays.stream(a).forEach(comparable -> {
            System.out.println(comparable + "");
        });
    }

    protected boolean isSorted(Comparable[] a) {
        for (int i = 1, len = a.length; i < len; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String [] args){
        int a = 0;
        System.out.println(a++);
        System.out.println(a++);
        System.out.println(a);
    }
}
