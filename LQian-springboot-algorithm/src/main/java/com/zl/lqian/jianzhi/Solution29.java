package com.zl.lqian.jianzhi;

import java.util.ArrayList;

/**
 * @Author zl
 * @Date 2020-04-08
 * @Des ${todo}
 */
public class Solution29 {


    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     *
     * 排序 取k值
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k == 0)
            return list;
        int[] arr = new int[k + 1];//数组下标0的位置作为哨兵，不存储数据
        //初始化数组
        for (int i = 1; i < k + 1; i++)
            arr[i] = input[i - 1];
        buildMaxHeap(arr, k + 1);//构造大根堆
        for (int i = k; i < input.length; i++) {
            if (input[i] < arr[1]) {
                arr[1] = input[i];
                adjustDown(arr, 1, k + 1);//将改变了根节点的二叉树继续调整为大根堆
            }
        }
        for (int i = 1; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }


    public void buildMaxHeap(int[] arr, int length) {
        if (arr == null || arr.length == 0 || arr.length == 1)
            return;
        for (int i = (length - 1) / 2; i > 0; i--) {
            adjustDown(arr, i, arr.length);
        }
    }

    /**
     *  堆排序中对一个子二叉树进行堆排序
     * @param arr
     * @param k
     * @param length
     */
    public void adjustDown(int[] arr, int k, int length) {
        arr[0] = arr[k];//哨兵
        for (int i = 2 * k; i <= length; i *= 2) {
            if (i < length - 1 && arr[i] < arr[i + 1])
                i++;//取k较大的子结点的下标
            if (i > length - 1 || arr[0] >= arr[i])
                break;
            else {
                arr[k] = arr[i];
                k = i; //向下筛选
            }
        }
        arr[k] = arr[0];
    }
}
