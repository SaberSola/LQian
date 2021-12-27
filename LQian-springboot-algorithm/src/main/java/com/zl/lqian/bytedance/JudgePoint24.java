package com.zl.lqian.bytedance;

/**
 * 679. 24 点游戏
 */
public class JudgePoint24 {


    /**
     * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [4, 1, 8, 7]
     * 输出: True
     * 解释: (8-4) * (7-1) = 24
     * 示例 2:
     * <p>
     * 输入: [1, 2, 1, 2]
     * 输出: False
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/24-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param cards
     * @return
     */
    public boolean judgePoint24(int[] cards) {
        return helper(new double[]{cards[0], cards[1], cards[2], cards[3]});
    }
    private static final double TARGET = 24;
    private static final double EPISLON = 1e-6;

    private boolean helper(double[] nums) {
        if (nums.length == 1){
            return Math.abs(nums[0] -TARGET) < EPISLON;
        }

    }

}
