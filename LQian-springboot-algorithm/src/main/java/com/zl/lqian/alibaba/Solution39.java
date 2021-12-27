package com.zl.lqian.alibaba;

/**
 * @author zhanglei
 */
public class Solution39 {


    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        /**
         *  [3,4,5,1,2]
         *  二分查找
         *  有序数组查找,首先想到二分查找法
         */
        int len = numbers.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (numbers[mid] > numbers[left]) {
                left = mid + 1;
            } else if (numbers[mid] == numbers[right]) {
                right = right - 1;
            } else {
                right = mid;
            }
        }
        return numbers[left];
    }

}
