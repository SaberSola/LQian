package com.zl.lqian.jianzhi;

import com.zl.lqian.cs.algs4.Stack;

/**
 * @Author zl
 * @Date 2020-04-07
 * @Des 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 */
public class Solution21 {


    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列
     *  1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     * （注意：这两个序列的长度是相等的）
     *
     * 入栈和出栈是否和数组相同
     *
     * @param pushA
     * @param popA
     * @return
     */


    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        int[] b = {4,5,3,2,1};
        System.out.println(IsPopOrder(a,b));
    }
    public static  boolean IsPopOrder(int [] pushA,int [] popA) {
        int len = pushA.length;
        Stack<Integer> s = new Stack();
        for(int i=0, j=0;  i < len; i++){
            s.push(pushA[i]);
            while(j < len && s.peek() == popA[j]){
                s.pop();
                j = j+1;
            }
        }
        return s.isEmpty();
    }

}
