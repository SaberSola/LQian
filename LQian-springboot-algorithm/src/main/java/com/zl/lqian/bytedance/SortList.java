package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //断开两个链表
        ListNode temp = slow.next;
        slow.next = null;
        //继续归并
        ListNode left = sortList(head);
        ListNode right = sortList(temp);
        //开始合并链表
        ListNode newHead = new ListNode(0);
        ListNode newTemp = newHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                newTemp.next = left;
                left = left.next;
            } else {
                newTemp.next = right;
                right = right.next;
            }
            newTemp = newTemp.next;
        }
        newTemp.next = left != null ? left : right;
        return newHead.next;
    }
}
