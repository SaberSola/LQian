package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-14
 * @Des ${todo}
 */
public class Solution39 {


    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 注意:
     * 不能使用代码库中的排序函数来解决这道题。
     *
     * 示例:
     * 快速排序
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     * @param nums
     */
    public void sortColors(int[] nums) {
        int lenth = nums.length;
        if(lenth < 2){
            return;
        }

        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2
        int zero = 0;
        int two  = lenth;
        int  i = 0;

        while (i < two){
            if(nums[i] == 0){
                //和zero换位置
                swap(nums,i,zero);
                zero++;
                i++;
            }else if (nums[i] == 1){
                i++;
            }else { //为2
                //和two 换位置
                two--;
                swap(nums, i, two);
            }
        }

    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
