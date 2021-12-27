package com.zl.lqian.bytedance;

/**
 * 42. 接雨水
 */
public class Trap {

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {

        /**
         * 思路 按列计算每一列存储的雨水
         *  min(lHeight, rHeight) - height。
         *  当前列的的左边最大和右边最大的值的最小值 - 当前列的高度,就是当前列可以存的雨水
         */
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            //第一列和最后一列不需要
            if (i == 0 || i == height.length - 1) {
                continue;
            }
            int rHeight = height[i]; // 记录右边柱子的最高高度
            int lHeight = height[i]; // 记录左边柱子的最高高度
            //找出右边最大值
            for (int r = i + 1; r < height.length; r++) {
                if (height[r] > rHeight) rHeight = height[r];
            }
            //找出左边最大值
            for (int l = i - 1; l >= 0; l--) {
                if (height[l] > lHeight) lHeight = height[l];
            }
            int h = Math.min(lHeight, rHeight) - height[i];
            if (h > 0) {
                sum += h;
            }
        }
        return sum;

    }


}
