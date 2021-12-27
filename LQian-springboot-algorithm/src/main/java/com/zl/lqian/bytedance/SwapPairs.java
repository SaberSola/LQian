package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

/**
 * 24. 两两交换链表中的节点
 */
public class SwapPairs {


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        //递归操作
        ListNode temp = head.next;
        //指向下一个节点头
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }
}
