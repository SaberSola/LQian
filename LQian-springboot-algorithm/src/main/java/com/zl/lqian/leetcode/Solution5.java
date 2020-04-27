package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-26
 * @Des ${todo}
 */
public class Solution5 {


    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 动态规划
     * boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文
     * 我们用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。试想如果 dp[l][r]=true，我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符，是不是减少了很多重复计算。
     * 进入正题，动态规划关键是找到初始状态和状态转移方程。
     * 初始状态，l=r 时，此时 dp[l][r]=true。
     * 状态转移方程，dp[l][r]=true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1]=true
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度
        //true:就是回文, false:不是回文
        boolean[][] dp = new boolean[strLen][strLen];
        for (int r = 0; r<strLen; r++){
            for (int l = 0; l < r; l++){
                if (s.charAt(l) == s.charAt(r) && (r - l <=2 || dp[l + 1][r-1])){
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}
