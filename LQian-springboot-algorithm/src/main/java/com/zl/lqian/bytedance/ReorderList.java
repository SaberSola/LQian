package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class ReorderList {

    /**
     * @param head
     */
    public void reorderList(ListNode head) {
        /**
         *  给定链表 1->2->3->4, 重新排列为 1->4->2->3.
         *
         *   给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
         *
         *    1:把原链表从中间折断,快慢指针
         *      1 --> 2 -- >3
         *      4 --> 5
         *    2: 翻转第二段
         *       1 --> 2 -- >3
         *       5 --> 4
         *    3: 合并两条链表
         *       1 -- > 5 -- > 2 ---> 4 ---> 3
         */
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        //链表切分
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = slow.next;
        //断开链表
        slow.next = null;
        //翻转第二个链表
        newHead = reverseList(newHead);
        //合并两个链表
        mergeList(head,newHead);
    }

    //翻转链表
    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = curNext;
        }
        return pre;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return;
        }
        while (l1 != null && l2 != null){
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;
            l1.next = l2;
            l1  = l1Next;
            l2.next = l1;
            l2 = l2Next;
        }
    }
}
