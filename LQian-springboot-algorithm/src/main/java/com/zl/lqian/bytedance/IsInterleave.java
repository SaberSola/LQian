package com.zl.lqian.bytedance;

/**
 * 97. 交错字符串
 */
public class IsInterleave {

    /**
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：s1 = "", s2 = "", s3 = ""
     * 输出：true
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/interleaving-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public boolean isInterleave(String s1, String s2, String s3) {
        //去定dp[][]数组的含义 dp[i][j]  s1的前i个字符串和s2的前j个字符能够拼接成s3的前i+j个字符串
        int m = s1.length(), n = s2.length();
        if (s3.length() != m + n) {
            return false;
        }
        //确定dp含义
        boolean[][] dp = new boolean[m][n];
        //dp[0][0] dp[i][0] dp[0][j]
        dp[0][0] = true;
        //dp[i][0]
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            }
        }
        // dp[0][j]
        for (int j = 1; j <= n; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = true;
            }
        }
        //其他情况，到达（i，j）可能由（i-1,j）点向下一步，选择 s1[i-1] 到达；也可能由 （i,j-1） 点向右一步，选择 s2[j-1] 到达；
        //dp[i,j] = (dp[i-1][j] &&s3[i+j-1] == s1[i-1]) || (dp[i][j-1] && s3[i+j-1] == s2[j-1])
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1))
                        || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
            }
        }
        return dp[m][n];
    }

}
