package com.zl.lqian.onehundred.String;

public class ReserveString {


    /**
     * 翻转字符串
     * @param str
     * @return
     */
    public char[] reverseString(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right){
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        return chars;
    }
}
