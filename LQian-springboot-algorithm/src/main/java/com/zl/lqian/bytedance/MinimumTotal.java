package com.zl.lqian.bytedance;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 * <p>
 * 输入：triangle = [[-10]]
 * 输出：-10
 */
public class MinimumTotal {


    public int minimumTotal(List<List<Integer>> triangle) {
        /**
         * [2]
         * [3,4]
         * [6,5,7]
         * [4,1,8,3]
         * 确定好dp[][]的含义 dp[i][j] 代表从三角形顶部走到第(i,j)的位置的最小路径和
         * 只能移动到相邻的节点,所以dp[i][j]只能从dp[i-1][j-1]和dp[i-1][j]移动
         * 确定好状态转移方程
         * dp[i][j]=min(dp[i−1][j−1],dp[i−1][j])+c[i][j]
         * c[i][j]代表当前数组的数字
         * 确定好边界值
         * 当j=0的时候
         * dp[i][0] = dp[i-1][0] + c[i][0];
         * 当处于最右,只能从 i-1的最左侧过来
         *  dp[i][i]= dp[i−1][i−1]+ dp[i][i]
         */

        int n = triangle.size();
        int[][] dp = new int[n][n];
        //起始值
        dp[0][0] = triangle.get(0).get(0);
        //开始foreach
        for (int i = 1; i < n; i++) {
            //处理边界
            dp[i][0] = dp[i - 1][0] + triangle.get(1).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            //处理最右边
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        //获取结果
        int minTotal = dp[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, dp[n - 1][i]);
        }
        return minTotal;

    }
}
