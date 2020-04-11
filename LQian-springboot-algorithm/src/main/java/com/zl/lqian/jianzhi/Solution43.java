package com.zl.lqian.jianzhi;

import java.util.ArrayList;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
 * 使得他们的和正好是S，如果有多对数字的和等于S，
 * 输出两个数的乘积最小的。
 */
public class Solution43 {

    /**
     * 输入一个递增排序的数组和一个数字S，
     * 在数组中查找两个数，使得他们的和正好是S，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的。
     *
     * 还是双指针发
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        //边界条件
        if (array == null || array.length <= 1) {
            return result;
        }
        int smallIndex = 0;
        int bigIndex = array.length - 1;
        while (smallIndex < bigIndex) {
            if ((array[smallIndex] + array[bigIndex]) == sum) {
                result.add(array[smallIndex]);
                result.add(array[bigIndex]);
                break;
            } else if ((array[smallIndex] + array[bigIndex]) < sum) {
                smallIndex++;
            } else {
                bigIndex--;
            }
        }
        return result;
    }
}
