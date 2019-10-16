package com.zl.lqian.jianzhioffer;

import java.util.Stack;

/**
 * @Author zl
 * @Date 2019-10-11
 * @Des 定义一个栈，在该类型中实现一个能够得到栈的最小元素的Min函数。
 */
public class StackWithMin {

    static Stack<Integer> stack1 = new Stack<>();
    static Stack<Integer> stack2 = new Stack<>();

    public static void push(int node) {
        stack1.push(node);
        if (stack2.isEmpty()) {
            stack2.push(node);
        } else {
            if (stack2.peek() > node) {
                stack2.push(node);
            }
        }
    }

    public static void pop() {
        if (stack1.pop() == stack2.peek()) {
            stack2.pop();
        }
    }

    public static int top() {
        return stack1.peek();
    }

    public static int min() {
        return stack2.peek();
    }

}
