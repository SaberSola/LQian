package com.zl.lqian.onehundred.slicewindow;

import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {

    /**
     * 第438. 找到字符串中所有字母异位词
     * 输入:s: "cbaebabacd" p: "abc"
     * <p>
     * 输出:[0, 6]
     * <p>
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {

        if (s == null || p == null || s.length() < p.length()) return new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        int[] pArr = new int[26];
        int[] sArr = new int[26];

        for (int i = 0; i < p.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
            pArr[p.charAt(i) - 'a']++;
        }

        int i = 0;
        int j = p.length();

        // 窗口大小固定为p的长度
        while (j < s.length()) {
            if (isSame(pArr, sArr)) {
                list.add(i);
            }
            //sArr[s.charAt(i) - 'a']-- 左指针位置处字母减1
            sArr[s.charAt(i) - 'a']--;
            i++;
            //sArr[s.charAt(j) - 'a']++ 右指针位置处字母加1
            sArr[s.charAt(j) - 'a']++;
            j++;
        }

        if (isSame( pArr, sArr)) {
            list.add(i);
        }

        return list;
    }

    public static boolean isSame(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; ++i) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd","abc"));
    }
}
