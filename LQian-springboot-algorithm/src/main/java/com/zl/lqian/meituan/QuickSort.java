package com.zl.lqian.meituan;

import com.zl.lqian.bytedance.Solution3;

import java.util.Random;

public class QuickSort {
    static Random random = new Random();

    public static void sort(int[] a, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int index = part(a, start, end);
        sort(a, start, index - 1);
        sort(a, index + 1, end);
    }

    /**
     * @param a
     * @param start
     * @param end
     * @return
     */
    public static int part(int[] a, int start, int end) {
        int pivot = random.nextInt(end - start + 1) + start;
        swap(a, end, pivot);
        return partion(a, start, end);
    }

    public static int partion(int[] a, int l, int r) {
        //获取随机数
        int x = a[r];
        int i = l - 1;
        for (int j = l; j < r; ++j) {
            //小于基准值的,都放到i的左边
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        //上边for把所有小于基准值的x的都设置在了i的左边
        //5. 既然已经将<x的值都放在一边了，现在将x也就是nums[r] 跟nums[i+1]交换，从而分成两个区间[l.i+1]左, [i+2, r]右，左边区间的值都小于x
        //交换基准位置
        swap(a, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{1, 2, 3, 9, 7, 5, 4};
        sort(nums, 0, nums.length);
        System.out.println(nums);
    }
}
