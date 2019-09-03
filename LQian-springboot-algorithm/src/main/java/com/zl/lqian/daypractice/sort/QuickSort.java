package com.zl.lqian.daypractice.sort;

import com.zl.lqian.cs.algs4.StdRandom;
import com.zl.lqian.daypractice.Example;

/**
 * @Author zl
 * @Date 2019-09-02
 * @Des ${todo}
 */
public class QuickSort extends Example {


    /**
     * 快速排序思想
     * 选一个基础点 m 将大于m归为一组 小于m的归为一组
     * 在将俩组分别排序
     * @param a
     */
    @Override
    protected void sort(Comparable[] a) {
        StdRandom.shuffle(a);    // 消除对输入的依赖
        sort(a,0, a.length - 1);
    }

    private void sort(Comparable[] a,int low ,int high){
        if (high <= low) return;
        //开始寻找分割点并开始分割
        int partition = partition(a,low,high);
        //开始俩边排序
        sort(a,low,partition-1);
        sort(a,partition + 1,high);

    }

    private int partition(Comparable[] a,int low,int high){
        //定义左右扫描指针 i: 左指针 j:右指针
        int i = low,j = high + 1;
        Comparable v = a[low];
        //开始移动元素 左右扫描
        while (true){
            //小于v的属于lwo位
            while (less(a[++i],v)){ //从左网右扫描 a[++i] > v 时候退出循环
                if (i == high) break;
            }
            while (less(v,a[--j])){ //从右往左扫描 a[--j] < v 时候退出循环
                if (j == low) break;
            }
            //直到i 和 j 指针发生碰撞
            if (i >= j){break;}     //指针发生碰撞 结束 退出孙桓
            exch(a,i,j);            //交换 此时a[i] > a[j] 交换数组位置
        }//退出循环 i >= j; //此时的a[low] > a[j] 交换low j的位置  此时就是 a[low.... j-1] < a[j] < a[j + 1 .....high]
        exch(a,low,j);
        return j;

    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        Integer a[] = {3, 11, 5, 6, 4, 1, 9, 30,98,6};
        quickSort.sort(a);
        quickSort.show(a);
    }
}
