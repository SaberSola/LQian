package com.zl.lqian.onehundred.arrary;

import java.util.Arrays;

public class RotateArrary {


    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     *
     * @param nums
     * @param k
     * @return
     */
    public void rotate(int[] nums, int k) {
        /**
         *   k = 3
         *   1  2  3  4  5  6 7
         *   翻转所有的元素
         *   7  6  5  4  3  2 1
         *   翻转前K个元素
         *   5  6  7  4  3  2 1
         *   接着翻转后 len - k个元素
         *   5  6  7  1  2  3 4
         */
        k=k%nums.length;//获取分割点下标
        reverse(nums,0,nums.length-1);
        reverse(nums,0, k-1);
        reverse(nums, k,nums.length-1);
        Arrays.stream(nums).forEach(o->{System.out.print(o);});
    }


    private void reverse(int[] nums,int s,int e) {
        int tmp = 0;
        while (s < e) {
            tmp=nums[s];
            nums[s]=nums[e];
            nums[e]=tmp;
            s++;
            e--;
        }

    }

    public static void main(String[] args) {
        int num[] = {1,2,3,4,5,6,7};
        RotateArrary arrary = new RotateArrary();
        arrary.rotate(num,3);
    }
}
