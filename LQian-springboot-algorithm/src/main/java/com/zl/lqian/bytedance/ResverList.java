package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class ResverList {


    /***
     * 翻转列表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        /**
         *     1 --> 2 -- > 3
         *  1 <-- 2 <---3
         */
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        /**
         *   1 --> 2 --> 3
         *   3 --> 2 --> 1
         *
         *
         *   pre = null;
         *   cur = 1
         *   temp = cur.next; 2
         *   cur.next = pre (1.next = null)
         *   pre = cur; pre = 1
         *   cur = temp cur = 2
         *   此时
         *
         *   pre = 1 cur = 2
         *   1 <-- 2 -- > 3
         *
         */
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseList3(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
