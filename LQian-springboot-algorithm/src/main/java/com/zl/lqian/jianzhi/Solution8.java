package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-05
 * @Des 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class Solution8 {


    /**
     * n = 1;
     * 首先可知，第一阶有只能一步，一种；，第二阶可以两次一步、一次两步两种
     * f(1) = 1 f(2) = 2
     * 若楼梯阶级 n = 3
     * 跳 2 步到 3：剩下的是第一步没跳，起始跳到第一步只有一种
     * 跳 1 步到 3：剩下的是第二步没跳，起始跳到第二步有两种
     *
     * 若楼梯阶级 n = n
     *      跳 2 步到 n：剩下的是第 n - 2 步没跳，起始跳到第 n - 2 步设它为 pre2 种
     *      跳 1 步到 n：剩下的是第 n - 1 步没跳，起始跳到第 n - 1 步设它为 pre1 种
     *
     *     同时可以发现第 n 阶的解法，只要用到 n - 1 和 n - 2 阶是多少，其他的不用考虑，因此用两个变量临时存下来即可
     *   dp(i) = dp(i-2) + dp(i-1)
     *
     *
     * @param n
     * @return
     */
    public static Integer jumpFlower(Integer n){
        if (n <= 2){
            return n;
        }
        /**
         * 当n = 3的时候
         * 总次数 = 2 + 1 = 3中
         */
        int pre2 = 1, pre1 = 2;
        for (int i = 3; i <=n; i ++){
            //dp[i] = dp[i-1] + dp[i-2]
            int cur = pre1 + pre2; //第三节 = 第二节 + 第二节的和
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;

    }

    public static Integer jump(Integer n){
        if (n <=2){
            return n;
        }
        int pre2 = 1, pre1 = 2;
        for (int i = 3; i <=n; i++){
            int cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    //不同路径
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


}
