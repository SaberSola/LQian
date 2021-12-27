package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

/**
 * 61. 旋转链表 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class RotateRight {


    /**
     * 旋转链表 k
     * 1 -- 2 -- 3 -- 4
     * 3 -- 4 -- 1 -- 2
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        /**
         * 断开第K节点链表 然后将最后一个节点只想头结点即可
         */
        if (head == null || head.next == null) {
            return head;
        }
        int len = 1;
        ListNode dummy = head;
        while (dummy.next != null) {
            dummy = dummy.next;
            len = len + 1;
        }
        k = k % len;
        //变成环状节点
        dummy.next = head;
        for (int i = 0; i < k; i++) {
            dummy = dummy.next;
        }
        head = dummy.next;
        dummy.next = null;
        return head;
    }

}
