package com.zl.lqian.bytedance;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 * @author zhanglei
 */
public class LengthOfLongestSubstring {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0 ; i < s.length(); i ++){
            if (map.containsKey(s.charAt(i))){//发现重复的字符创
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;
    }



    /**
     * 无重复的最长字符串
     * 思路是两个指针, left max分表标识起始和终止的
     *
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        /**
         *   a  b  b  d  e  f  g  a
         *   a  0  left = 0 max = 1
         *   b  1  left = 0 max = 2
         *   b  2  left = 2 max = 2
         *   d  3  left = 2 max = 2
         *   e  4  left = 2 max = 3
         *   f  5  left = 2 max = 4
         *   g  6  left = 2 max = 5
         *   a  7  left = 2 max = 6
         *
         */
        for (int i = 0 ; i < s.length(); i ++){
            //map包含,发现重复字符穿
            if (map.containsKey(s.charAt(i))){//发现重复字符串
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;

    }



    public static void main(String[] args) {
        /**
         *   a  b  b  d  e  f  g  a
         *   1  2  0  4  5  6  7
         *
         */
        System.out.println(lengthOfLongestSubstring("abbdefga"));
    }


}
