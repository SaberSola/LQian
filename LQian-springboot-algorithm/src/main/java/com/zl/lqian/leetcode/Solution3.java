package com.zl.lqian.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author zl
 * @Date 2020-04-26
 * @Des ${todo}
 * 3. 无重复字符的最长子串
 */
public class Solution3 {


    /**
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     * 采用双指针 滑动窗口
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1){
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length() && left < s.length()){
            if (!set.contains(s.charAt(right))){
                set.add(s.charAt(right++));
                max = Math.max(max, right - left);
            }else {
                set.remove(s.charAt(left++));
            }
        }
        return max;
    }

}
