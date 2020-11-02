package com.zl.lqian.onehundred.String;

public class FirstUniqChar {

    /**
     * 字符串中的第一个唯一字符
     *
     * @param str
     * @return
     */
    public int firstUniqChar(String str) {

        int[] arr = new int[26];
        char[] s = str.toCharArray();
        //利用字节的acIIma
        for (int i = 0; i < s.length; i++) {
            arr[s[i] - 'a']++;
        }
        for (int i = 0; i < s.length; i++) {
            if (arr[s[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
