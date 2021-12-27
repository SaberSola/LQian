package com.zl.lqian.bytedance;

public class LongestCommonPrefix {

    /**
     * 14. 最长公共前缀
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        /**
         * 编写一个函数来查找字符串数组中的最长公共前缀。
         *
         * 如果不存在公共前缀，返回空字符串 ""。
         * 固定一个,顺序比较就好了
         */
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String compare = strs[i];
            while (compare.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] s = new String[]{"flower", "flow", "flight"};
        longestCommonPrefix(s);
    }
}
