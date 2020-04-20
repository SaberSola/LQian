package com.zl.lqian.jianzhi;

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
        int res = 0;
        if (s.length() < 2) {
            return 0;
        }
        //需要存个数组
        int max = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')'){

            }else {

            }
        }


        return 0;
    }
}
