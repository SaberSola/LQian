package com.zl.lqian.bytedance;

/**
 * 402. 移掉 K 位数字
 */
public class RemoveKdigits {

    /**
     * 移掉 K 位数字
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     * 输入：num = "1432219", k = 3
     * 输出："1219"
     * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        /**
         *
         * 首先确定 移除后的数组长度 num.lenth - k;
         * 然后依次对比
         *
         */
        if (num.length() == k) {
            return "0";
        }
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (k > 0 && stack.length() != 0 && stack.charAt(stack.length() - 1) > c) {
                stack.setLength(stack.length() - 1);
                k--;
            }
            if (c == '0' && stack.length() == 0) {
                continue;
            }
            stack.append(c);
        }
        String result = stack.substring(0, stack.length() - k < 1 ? 0 : stack.length() - k).toString();
        return result.length() == 0 ? "0" : result;
    }
}
