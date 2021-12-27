package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 示例 1:
 * <p>
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 * <p>
 * 输入：s = ""
 * 输出：' '
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstUniqChar {

    /**
     * hash法
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

}
