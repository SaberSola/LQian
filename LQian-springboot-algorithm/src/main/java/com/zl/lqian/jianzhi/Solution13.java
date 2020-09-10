package com.zl.lqian.jianzhi;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des ${todo}
 */
public class Solution13 {


    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * <p>
     * 1 3 2 4 5 6
     * <p>
     * 1 3 2 4 5 6    第一次
     * 1 3 2 5 4 6    第二次
     * 1 3 5 2 4 6    第三次
     * 排序后
     * 135 246
     */

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6};
        reOrderArray(a);
    }

    public static void reOrderArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j + 1 <= i; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
                    swap(array, j, j + 1);
                    Arrays.stream(array).forEach(System.out::print);
                    System.out.println("******************");
                }
            }
        }
    }


    public static void swap(int array[], int i, int j) {
        int t = array[j];
        array[j] = array[i];
        array[i] = t;
    }


    public void reOrderArray2(int array[]) {
        for (int i = array.length - 1; i > 0; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
}
