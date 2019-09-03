package com.zl.lqian.daypractice.sort;

import com.zl.lqian.cs.algs4.StdRandom;
import com.zl.lqian.daypractice.Example;

/**
 * @Author zl
 * @Date 2019-09-02
 * @Des ${todo}
 * 三向切分的快速排序
 */
public class QuickSortExt extends Example {

    @Override
    protected void sort(Comparable[] a) {
        StdRandom.shuffle(a);    // 消除对输入的依赖
        sort(a,0, a.length - 1);
    }

    private void sort(Comparable[] a,int low ,int high){
        if (high <= low) return;
        int lt = low,i = low + 1,gt = high;  //lt 低位 gt 高位
        Comparable v = a[low];               //比较的基准点
        while (i <= gt){  //i < 最高位
            int cmp = a[i].compareTo(v);
            if (cmp < 0){                         //此时a[i] < v lt低位需要和a[i] 更换位置
                exch(a,lt++,i++);
            }else if (cmp > 0){                   //此时a[i] > v  gt高位需要和a[i] 更换位置
                exch(a,i,gt--);
            }else {
                i++;
            }
        }//现在满足 a[low ... lt-1] < a[lt,gt] < a[gt +1 ...high]
        sort(a,low,lt -1);//排序 对左半部分
        sort(a,gt+1,high); //排序对右半部分
    }

    public static void main(String[] args) {
        QuickSortExt sort = new QuickSortExt();
        Integer a[] = {3, 11, 5, 6, 4, 1, 9, 30,98,6};
        sort.sort(a);
        sort.show(a);
    }
}
