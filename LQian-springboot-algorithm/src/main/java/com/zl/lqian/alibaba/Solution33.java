package com.zl.lqian.alibaba;

public class Solution33 {

    /**
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * <p>
     *  
     * <p>
     * 提示：
     * <p>
     * num1 和num2 的长度都小于 5100
     * num1 和num2 都只包含数字 0-9
     * num1 和num2 都不包含任何前导零
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        /**
         * 双指针
         */
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        //进位的标识
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            //像前移动
            i--;
            j--;
        }
        //处理比较长的字符创
        if (carry == 1) {
            res.append(1);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        addStrings("0", "0");
    }
}
