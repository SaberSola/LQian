package com.zl.lqian.bytedance;

public class LongestSubstring {

    /**
     * 395. 至少有 K 个重复字符的最长子串
     * <p>
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "aaabb", k = 3
     * 输出：3
     * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2：
     * <p>
     * 输入：s = "ababbc", k = 2
     * 输出：5
     * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        //分治
        char[] str = s.toCharArray();
        int[] cnt  = new int[26];
        int n = str.length;
        if ()
    }
}
