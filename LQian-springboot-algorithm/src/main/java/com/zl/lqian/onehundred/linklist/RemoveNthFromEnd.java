package com.zl.lqian.onehundred.linklist;

import com.zl.lqian.leetcode.ListNode;

public class RemoveNthFromEnd {

    /**
     * 删除倒数第N个
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode node = sentinel;//用于移动的指针
        ListNode current = sentinel;//需要删除的节点
        ListNode pre = sentinel; //操作删除current的节点
        int i = 1; //记录步骤
        while (node != null && node.next != null){
            if (i > n){
                pre = current;
                current = current.next;
            }
            node = node.next;
            i++;
        }
        pre.next=pre.next.next;
        return sentinel.next;
    }
}
