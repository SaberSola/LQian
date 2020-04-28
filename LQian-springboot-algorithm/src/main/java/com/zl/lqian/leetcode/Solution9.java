package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-27
 * @Des ${todo}
 */
public class Solution9 {

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 双指针求城际
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length < 1) {
            return 0;
        }
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int area = (r - l) * Math.min(height[l], height[r]);//面积
            max = Math.max(area, max);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }
}
