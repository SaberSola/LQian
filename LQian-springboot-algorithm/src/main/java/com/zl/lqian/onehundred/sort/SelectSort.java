package com.zl.lqian.onehundred.sort;

import java.util.Arrays;

public class SelectSort {

    /**
     * 选择排序
     * @param sourceArray
     * @return
     * @throws Exception
     */
    public int[] sort(int[] sourceArray) throws Exception {

        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < arr.length; i++){
            int min = i;
            for (int j = i+1; j < arr.length; j++){
                if(arr[i] < arr[j]){
                    //找到最小值
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;

    }

    
}
