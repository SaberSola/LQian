package com.zl.lqian.onehundred.slicewindow;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {


    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n  = s.length();
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0,left = 0; right < n; right++){
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(map.get(s.charAt(right)), left);
            }
            result = Math.max(result, right - left + 1);
            map.put(s.charAt(right), right + 1);
        }

        return result;
    }

}
