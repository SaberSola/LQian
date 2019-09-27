package com.zl.lqian.jianzhioffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author zl
 * @Date 2019-09-27
 * @Des 打印链表 倒叙
 */
public class PrintList {


    public static ArrayList<Integer> printListReverse1(Common.ListNode headNode) {

        ArrayList<Integer> list = new ArrayList<>();
        Stack<Common.ListNode> stack = new Stack<>();
        while (headNode != null) {
            stack.push(headNode);
            headNode = headNode.next;
        }

        while (!stack.empty()){
           list.add(stack.pop().val);
        }
        return list;
    }

    public static ArrayList<Integer> printListReverse2(Common.ListNode headNode) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        if (headNode != null) {
            if (headNode.next != null) {
                list = printListReverse2(headNode.next);
            }
            list.add(headNode.val);
        }
        return list;
    }

}
