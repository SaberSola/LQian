package com.zl.lqian.bytedance;

import com.zl.lqian.cs.algs4.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 227. 基本计算器 II
 */
public class Calculat {


    public static int calculate(String s) {
        //括号中的计算结果
        int res = 0;
        //当前的数字
        int num = 0;
        //符号 - +
        int sign = 1;
        //当右括号是,存储计算结果
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (c == ' ') {
                continue;
            }
            //如果当前是数字 判断数据是否 在0-9之间
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                //例如：123+4，只有当取到+时，才能确定123为当前的num
                if (i < len - 1 && '0' <= chars[i + 1] && chars[i + 1] <= '9') {
                    continue;
                }
                //如果当前字符为'+'或者'-'
            } else if (c == '+' || c == '-') {
                num = 0;
                //如果当前符号为+，则sign为+1
                //如果当前符号为-，则sign为-1
                sign = c == '+' ? 1 : -1;
            } else if (c == '(') {
                // 例如当前表达式为：'123+(...)'
                // 将res入栈
                stack.push(res);
                // 则将sign:+，入栈
                stack.push(sign);
                //将res=0,保存()计算结果
                res = 0;
                //重置sign状态
                sign = 1;
            } else if (c == ')') {
                //'('前边的符号出栈
                sign = stack.pop();
                //将num替换为括号计算结果
                num = res;
                //将res替换为括号前边的计算结果
                res = stack.pop();
            }
            //每遍历一次，得到一个res
            res += sign * num;
        }
        return res;
    }


    /**
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     *
     * 整数除法仅保留整数部分。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "3+2*2"
     * 输出：7
     * 示例 2：
     *
     * 输入：s = " 3/2 "
     * 输出：1
     * 示例 3：
     *
     * 输入：s = " 3+5 / 2 "
     * 输出：5
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int calculate2(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

}
