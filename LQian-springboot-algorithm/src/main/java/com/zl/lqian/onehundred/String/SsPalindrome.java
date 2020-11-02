package com.zl.lqian.onehundred.String;

public class SsPalindrome {

    /**
     * 判断是否是回文串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^0-9a-z]", "");
        char[] c = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (c[left] != c[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
