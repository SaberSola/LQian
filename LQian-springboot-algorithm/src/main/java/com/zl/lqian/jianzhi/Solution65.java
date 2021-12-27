package com.zl.lqian.jianzhi;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-20
 * @Des ${todo}
 */
public class Solution65 {


    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * ")()())"有效的为4
     * dp[i] = dp[i-2] + 2;
     * 行政楼
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int dp[] = new int[s.length()];
        if (s.length() < 2) {
            return 0;
        }
        Arrays.fill(dp, 0);
        //需要存个数组
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2) > -1 ? dp[i - dp[i - 1] - 2] : 0;
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
