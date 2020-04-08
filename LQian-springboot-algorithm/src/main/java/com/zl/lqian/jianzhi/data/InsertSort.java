package com.zl.lqian.jianzhi.data;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-08
 * @Des ${todo}
 * 插入排序
 */
public class InsertSort {


    /**
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * 从后往前查找 小的换位置
     *
     * @param sourceArray
     * @return
     */
    public static void sort(int[] sourceArray) {
        for (int i = 0; i < sourceArray.length; i++) {

            // 记录要插入的数据
            int tmp = sourceArray[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < sourceArray[j - 1]) {
                sourceArray[j] = sourceArray[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                sourceArray[j] = tmp;
            }
        }
        Arrays.stream(sourceArray).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int a [] = {1,5,4,6,2};
        InsertSort.sort(a);
    }
}
