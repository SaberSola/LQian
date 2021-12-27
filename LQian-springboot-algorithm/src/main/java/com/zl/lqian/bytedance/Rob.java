package com.zl.lqian.bytedance;

public class Rob {

    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        /**
         * 输入：[1,2,3,1]
         * 输出：4
         * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
         *      偷窃到的最高金额 = 1 + 3 = 4 。
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/house-robber
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         * dp[i] :表示偷到第K个房间时候最大值
         * 两种情况,偷第 i-1和不偷当前房间
         * dp[i] = max(dp[i -1 ], nums[k-1] + dp[k-2])
         */
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }


    /**
     * 打家劫舍2 首位相连的情况
     *
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // 第一间「必然不选」的情况
        int[][] f = new int[n][2];
        for (int i = 1; i < n - 1; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
            f[i][1] = f[i - 1][0] + nums[i];
        }
        int a = Math.max(f[n - 2][1], f[n - 2][0] + nums[n - 1]);

        // 第一间「允许选」的情况
        f[0][0] = 0; f[0][1] = nums[0];
        for (int i = 1; i < n - 1; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
            f[i][1] = f[i - 1][0] + nums[i];
        }
        int b = Math.max(f[n - 2][0], f[n - 2][1]);

        return Math.max(a, b);
    }
}
