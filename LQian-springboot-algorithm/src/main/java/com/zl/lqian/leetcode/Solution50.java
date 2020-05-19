package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-18
 * @Des ${todo}
 */
public class Solution50 {


    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "race a car"
     * 输出: false
     * 双指针法
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            //判断
            if (!Character.isLetterOrDigit(chars[left])) {
                left++;
                continue;
            }

            if (!Character.isLetterOrDigit(chars[right])) {
                right--;
                continue;
            }
            if (chars[left] != chars[right])
                return false;
            else {
                left++;
                right--;
            }

        }

        return true;

    }
}
