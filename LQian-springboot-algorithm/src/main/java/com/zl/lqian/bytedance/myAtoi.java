package com.zl.lqian.bytedance;

public class myAtoi {


    /**
     * 字符串 转 integer
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s.equals("")) {
            return 0;
        }
        int sign = 1;
        int index = 0;
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        if (index == s.length()) {
            return 0;
        }
        //判断开头是否是特殊符号
        if (s.charAt(index) > '9' || s.charAt(index) < '0') {
            if (s.charAt(index) == '+') {
                index++;
            } else if (s.charAt(index) == '-') {
                sign = -1;
                index++;
            } else {
                return 0;
            }
        }
        int res = 0;
        //注意边界
        //整数的最大值,与最小值的边界
        while (index < s.length() && s.charAt(index) <= '9' && s.charAt(index) >= '0') {
            if (sign == 1 && (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && s.charAt(index) > '7'))
            || (sign == -1 && (res * sign < Integer.MIN_VALUE / 10 || (res * sign == Integer.MIN_VALUE / 10 && s.charAt(index) > '8')))){
                return sign == 1 ? Integer.MAX_VALUE :  Integer.MIN_VALUE;
            }
            res *= 10;
            res += s.charAt(index) - '0';
            index++;
        }
        return res * sign;
    }
}
