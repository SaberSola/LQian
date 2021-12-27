package com.zl.lqian.bytedance;

/**
 * 45. 跳跃游戏 II
 */
public class Jump {

    /**
     * 45. 跳跃游戏 II
     * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * <p>
     * 假设你总是可以到达数组的最后一个位置。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 示例 2:
     * <p>
     * 输入: nums = [2,3,0,1,4]
     * 输出: 2
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int end = 0; //边界
        int maxPosition = 0;//最大距离
        int steps = 0; //总步数
        for (int i = 0; i < nums.length - 1; i++) {
            //跳到最最远距离
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }

        }
        return steps;

    }
}
