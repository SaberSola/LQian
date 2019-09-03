package com.zl.lqian.daypractice.sort;

import com.zl.lqian.daypractice.Example;

/**
 * @Author zl
 * @Date 2019-09-01
 * @Des ${todo}
 */
public class SelectSort extends Example {

    /**
     * 选择排序
     * 1:找到元素中最小的和将他和第一个交换
     * 2:在剩下的元素中重复执行第一步
     *
     * @param a
     */
    @Override
    protected void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {  //外层循环
            int min = i; //一次比较的最小值的下标
            for (int j = i + 1; j < n; j++) {
                if (less(a[i], a[j])) {
                    min = j;
                    //最小值提前
                    exch(a, i, min);
                }
            }
        }
    }

    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        Integer a[] = {0,3, 11, 5, 6, 4, 1, 9, 5};
        selectSort.sort(a);
        selectSort.show(a);
    }
}
