package com.zl.lqian.daypractice.sort;

import com.zl.lqian.daypractice.Example;

/**
 * @Author zl
 * @Date 2019-09-01
 * @Des ${todo}
 */
public class InsertSort extends Example {


    /**
     * 插入排序
     * a[i] 依次和 a【i-1】a[i-a] 作比较 换位置
     *
     * @param a
     */
    @Override
    protected void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            //依次和a[i - 1] a[i - 2]比较
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    //换位置
                    exch(a, j, j - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        InsertSort selectSort = new InsertSort();
        Integer a[] = {0, 3, 11, 5, 6, 4, 1, 9};
        selectSort.sort(a);
        selectSort.show(a);
    }
}
