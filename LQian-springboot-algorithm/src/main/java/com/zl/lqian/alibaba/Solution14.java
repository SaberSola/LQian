package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.ListNode;

public class Solution14 {


    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     *
     *
     *     node1 --> node2 --> node3
     *
     *     node1-->node2 --> node3
     *
     *     pre和cur双指针
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur =head;
        ListNode pre = null;
        /**
         *
         *      head -- > node1 -->node2
         *      cur = head
         */
        while (cur != null){
            ListNode next =  cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 反转链表
     *      1 --> 2 -- 3
     *
     *      pre
     *      1  <--  2 -- >3
     *             cur
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur !=null){
            ListNode next = cur.next;
            cur.next =pre;
            pre =cur;
            cur = next;
        }
        return pre;
    }
}
