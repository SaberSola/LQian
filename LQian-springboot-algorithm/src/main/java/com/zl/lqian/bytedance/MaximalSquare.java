package com.zl.lqian.bytedance;

public class MaximalSquare {

    /**
     * 221. Maximal Square
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        /**
         *           1    0   1   0   0
         *
         *           1    0   1   1   1
         *
         *           1    1   1   1   1
         *
         *           1    0   0   1   0
         *
         *         dp[i，j] 表示到二维数组最大的正方形
         *          外层都是0
         *           1   0   1   0   0
         *
         *           1   0   1   1   1
         *
         *           1   1   1   2   2
         *
         *           1   0   0   1   0
         *
         *     if matrix[i][j] = 1
         *     dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1]) +1
         *
         */
        int max = 0;
        //通过dp返程计算正方形的变成
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return max;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    //边界填充
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
