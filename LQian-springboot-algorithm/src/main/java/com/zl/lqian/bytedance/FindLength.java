package com.zl.lqian.bytedance;

public class FindLength {

    /**
     * 718. 最长重复子数组
     * 最长重复子数组
     * 输入：
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出：3
     * 解释：
     * 长度最长的公共子数组是 [3, 2, 1] 。
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {

        /**
         *     1   2   3   2   1
         *  3  0   0   1   0   0
         *  2  0   0   1   2   0
         *  1  0   0   1   2   3
         *  4  0   0   0   0   0
         *  7  0   0   0   0   0
         *
         * 如果 A[i-1] != B[j-1]， 有 dp[i][j] = 0
         * 如果 A[i-1] == B[j-1] ， 有 dp[i][j] = dp[i-1][j-1] + 1
         *
         */

        int n = A.length;
        int m = B.length;
        int max = 0;
        int[][] dp = new int[n + 1][m + 1];

        for (int i=1;i<=n;i++) {

            for (int j=1;j<=m;j++) {

                if (A[i - 1] == B[j - 1]) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {

                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
