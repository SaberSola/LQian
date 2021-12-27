package com.zl.lqian.bytedance;

public class LongestPalindrome {

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 5. 最长回文子串
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        /**
         * 如果 dp[i][j]是回文串 那么 dp[i+1][j-1]也必是回文串
         *
         */
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        //填充
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int maxLen = 1;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
}
