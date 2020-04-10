package com.zl.lqian.jianzhi.data;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-09
 * @Des ${todo}
 */
public class ShellSort {


    /**
     * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序
     * ；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止、
     * 分组,然后进行插入排序 知道每组的成员为1
     */
    public static void shellSort(int[] a) {
        int n = a.length;
        int temp;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                temp = a[i];
                int prex = i - gap; //前边的数据
                while (prex >= 0 && a[prex] > temp) {
                    a[prex + gap] = a[prex];
                    prex -= gap;
                }
                a[prex + gap] = temp;
            }
            gap = gap/2;
        }
        Arrays.stream(a).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int a [] = {1,8,6,5,4,3,9,10};
        ShellSort.shellSort(a);
    }
}
