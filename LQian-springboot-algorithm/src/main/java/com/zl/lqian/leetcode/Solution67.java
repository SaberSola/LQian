package com.zl.lqian.leetcode;

import java.util.Stack;

/**
 * @Author zl
 * @Date 2020-06-09
 * @Des 实现最小栈
 */
public class Solution67 {


    Stack<Integer> stack;
    Stack<Integer> min_stack;
    /**
     * Solution67
     */
    /** initialize your data structure here. */
    public Solution67() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(min_stack.isEmpty() || x <= min_stack.peek())
            min_stack.push(x);

    }

    public void pop() {
        if(stack.pop().equals(min_stack.peek()))
            min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}
