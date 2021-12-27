package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.ListNode;

public class Solution4 {


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode head_before = new ListNode(0);
        head_before.next = head;
        ListNode pre = head_before;
        while (head != null) {
            ListNode tail = pre;
            //判断剩余的是否 < k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return head_before.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return head_before.next;

    }

    /**
     * 翻转list
     * head/p next/p.next
     * pre   1         2           3
     * pre   2 --- 1 --- 3
     *
     * @param head
     * @param tail
     * @return
     */
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode pre = tail.next;
        ListNode p = head;
        while (pre != tail) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return new ListNode[]{tail, head};
    }


    /**
     * preNode --> node1/p ---> node2
     * LisNode next = node2;
     * node2 = pre
     * preNode---node1/p --- preNode
     * pre = p;
     * node1/p ---node1/p --- preNode
     * p = next;
     * node2 node1/p --pre
     */


    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        //pre为每次翻转的头结点
        ListNode pre = dump;
        //每次翻转的尾巴节点
        ListNode end = dump;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            //翻转的节点小于K则不翻转
            if (end == null) {
                break;
            }
            //断开链表,记录end的下一个节点
            ListNode next = end.next;
            end.next = null;
            //反转链表的头结点
            ListNode start = pre.next;
            pre.next = reverse(start);
            //重新连接
            start.next = next;
            pre = start;
            end = start;
        }
        return dump.next;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        //前一个节点
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
