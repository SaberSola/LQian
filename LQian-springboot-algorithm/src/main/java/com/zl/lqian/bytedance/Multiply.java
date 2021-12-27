package com.zl.lqian.bytedance;

public class Multiply {


    /**
     * 字符串相乘
     * 43. 字符串相乘
     * 遍历 num2 每一位与 num1 进行相乘，将每一步的结果进行累加。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";
        for (int i = num2.length() - 1; i >= 0; i++) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            //开始补0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';
            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;

    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

}
