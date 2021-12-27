package com.zl.lqian.alibaba;

import java.util.*;

/**
 * 88. 合并两个有序数组
 */
public class Solution9 {

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。
     * 你可以假设nums1 有足够的空间（空间大小大于或等于m + n）来保存 nums2 中的元素。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /**
         * 输入：
         * nums1 = [1,2,3,0,0,0], m = 3
         * nums2 = [2,5,6],       n = 3
         *
         * 输出：[1,2,2,3,5,6]
         */
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        //copy数组的指针
        int p1 = 0;
        //nums2数组的指针
        int p2 = 0;
        //num1数组的指针
        int p = 0;
        while (p1 < m && p2 < n) {
            //开始比较
            if (nums1_copy[p1] < nums2[p2]) {
                nums1[p++] = nums1_copy[p1++];
            } else {
                nums1[p++] = nums2[p2++];
            }
        }
        //判断剩余数据是否还有长度
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }


    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 字符串乘
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

        for (int i = num2.length() - 1; i >= 0; i--) {
            //zhuwei相乘,然后相加
            int carry = 0;
            //第i位数字与num1相乘的数字
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';
            //num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            //求和
            res = addStrings(res, temp.reverse().toString());

        }

        return res;

    }

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
