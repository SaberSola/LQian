package com.zl.lqian.onehundred.String;

public class Kmp {


    /**
     * 暴力法匹配
     * @param haystack
     * @param needle
     * @return
     */
    public static int bFSearch(String haystack, String needle) {
        if (haystack == null || haystack.length() < 1 || needle == null || needle.length() < 1) {
            return -1;
        }
        char[] hChars = haystack.toCharArray();
        char[] nChars = needle.toCharArray();
        int hlen = hChars.length;
        int nlen = nChars.length;
        int i = 0;
        int j = 0;
        while (i < hlen && j < nlen){
            if (hChars[i] == nChars[j]){
                i++;
                j++;
            }else {
                j=0;
                i-= j-1;
            }

        }
        if (j == nlen){
            return i - j;
        }
        return -1;
    }



}
