package com.zl.lqian.alibaba;

import java.util.Deque;
import java.util.LinkedList;

public class Solution37 {

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public Solution37() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        if (stack2.isEmpty()) {
            return -1;
        } else {
            int value = stack2.pop();
            return value;
        }
    }
}
