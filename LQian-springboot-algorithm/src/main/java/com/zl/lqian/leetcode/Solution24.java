package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-10
 * @Des ${todo}
 */
public class Solution24 {


    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * 双指针法子
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        //遍历一次找到最高的主子
        int maxValue = 0;
        int maxAddr = 0;
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            if (maxValue < height[i]) {
                maxValue = height[i];
                maxAddr = i;
            }
        }

        //遍历左边的
        for (int left = 0; left < maxAddr; left++) {
            //储存水的高度就是左边最高的柱子减去当前的高度
            for (int i = left + 1; i <= maxAddr; i++) {
                if (height[i] < height[left]) {
                    result = result + height[left] - height[i];
                } else {
                    left = i;
                }
            }
        }

        //右半部分处理
        for (int right = height.length - 1; right > maxAddr; right--) {
            for (int i = right - 1; i >= maxAddr; i--) {
                if (height[i] < height[right]){
                    result = result + (height[right] - height[i]);
                }else {
                    right = i;
                }
            }
        }

        return result;


    }
}
