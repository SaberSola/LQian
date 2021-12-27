package com.zl.lqian.alibaba;

public class Solution13 {


    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例：
     * <p>
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        char[] chArray = s.toCharArray();
        int nextWordStart = 0;
        while (nextWordStart < chArray.length) {
            // 找到每个单词的首尾
            int start = nextWordStart;
            int end = start;
            //占到每个单词的末尾
            while (end < chArray.length && chArray[end] != ' ') {
                end++;
            }
            //end - 1单词的最后一个
            end--;

            int currentWordEnd = end;
            // 翻转单词
            while (start < end) {
                char temp = chArray[start];
                chArray[start] = chArray[end];
                chArray[end] = temp;
                start++;
                end--;
            }
            //currentWordEnd + 1 是空格, + 2 是空格下一个单词
            nextWordStart = currentWordEnd + 2;
        }
        return new String(chArray);
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";

        System.out.println(reverseWords(s));
    }
}
