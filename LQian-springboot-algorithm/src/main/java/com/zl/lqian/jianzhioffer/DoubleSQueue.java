package com.zl.lqian.jianzhioffer;

import java.util.Stack;

/**
 * @Author zl
 * @Date 2019-09-27
 * @Des 用俩个栈实现队列
 */
public class DoubleSQueue {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }


    /**
     * 队列先进先出
     *
     * @return
     * @throws Exception
     */
    public int pop() throws Exception {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("栈为空！");
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }


    public static void main(String[] args) throws Exception {
        DoubleSQueue doubleSQueue7 = new DoubleSQueue();
        doubleSQueue7.push(2);
        doubleSQueue7.push(5);
        doubleSQueue7.push(8);
        System.out.println("出队列：" + doubleSQueue7.pop());

    }

}
