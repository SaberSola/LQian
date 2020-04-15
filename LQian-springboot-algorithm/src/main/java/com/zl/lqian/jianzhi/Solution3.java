package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author zl
 * @Date 2020-04-04
 * @Des 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class Solution3 {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        printList(a);
    }

    /**
     * 先入后除 考虑用栈存储
     *
     * @param arrys
     * @return
     */
    public static List<Integer> printList(List<Integer> arrys) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arrys.size(); i++) {
            stack.push(arrys.get(i));
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        list.forEach(System.out::println);
        return list;
    }

    /**
     * 反转List 使用栈
     *
     * @param arrys
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(Common.ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
         while (listNode!= null){
             stack.push(listNode.val);
             listNode = listNode.next;
         }
         while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }

}
