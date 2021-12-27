package com.zl.lqian.jianzhi;

import java.util.Stack;

/**
 * @Author zl
 * @Date 2020-04-04
 * @Des ${todo}
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 栈是先进后出
 */
public class Solution5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (stack1.size() != 0) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }


    public static void main(String[] args) {

    }

}
