package com.zl.lqian.jianzhi;

import com.zl.lqian.cs.algs4.Stack;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des
 *  定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法
 */
public class Solution20 {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> min   = new Stack<>();


    public void push(int node) {
        if (stack.isEmpty()){
            stack.push(node);
            min.push(node);
            return;
        }
        int totalNode = min.peek();
        if (totalNode > node){
            min.push(node);
        }else {
            min.push(totalNode);
        }
        stack.push(node);
    }

    public void pop() {
        stack.pop();
        min.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min.peek();
    }


}
