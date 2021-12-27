package com.zl.lqian.bytedance;

import java.util.Objects;

public class ReverseWords {

    /**
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (Objects.isNull(s)) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = s.split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            if (strings[i].length() == 0) {
                continue;
            }
            stringBuilder.append(strings[i]).append(" ");
        }
        String res = stringBuilder.toString().trim();
        return res;
    }

    /**
     * 557. 反转字符串中的单词 III
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        // 根据空格转为数组
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String i : arr) {
            // 当前字符串长度
            int l = i.length();
            char[] as = i.toCharArray();
            // 双指针替换
            for (int j = 0; j < l / 2; j++) {
                char t = as[j];
                as[j] = as[l - j - 1];
                as[l - j - 1] = t;
            }
            sb.append(as);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
