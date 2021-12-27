package com.zl.lqian.bytedance;

public class AddStrings {

    public String addStrings(String num1, String num2) {
        //给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        //表示进位
        int carry = 0;
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1;
        while (len1 >= 0 || len2 >= 0) {
            int n1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int n2 = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            len1--;
            len2--;
        }
        if (carry == 1){
            res.append(1);
        }
        return res.reverse().toString();
    }
}
