package com.zl.lqian.onehundred.arrary;

import java.util.Arrays;

public class ConvertZ {


    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R     a[]
     * E T O E S I I G   a[]
     * E   D   H   N     a[]
     * <p>
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * <p>
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     *
     * 之字形变化
     * 2row -2是一个周期
     *
     *
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1)return s;
        String[] arr = new String[numRows];
        Arrays.fill(arr, "");
        char[] chars = s.toCharArray();
        int len = chars.length;
        //一个周期
        int period = numRows * 2 - 2;
        for (int i = 0; i< len ; i ++){
            int mod = i % numRows;
            if (mod < numRows){
                arr[mod] += chars[i];
            }else {
                arr[period - mod] += String.valueOf(chars[i]);
            }
        }
        StringBuilder res = new StringBuilder();
        for (String ch : arr) {
            res.append(ch);
        }
        return res.toString();
    }


}


