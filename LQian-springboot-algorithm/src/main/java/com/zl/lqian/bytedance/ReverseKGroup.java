package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 */
public class ReverseKGroup {


    public ListNode reverseKGroup(ListNode head, int k) {
        //对于链表操作,需要一个虚拟头结点
        ListNode dump = new ListNode(0);
        //虚拟头结点指向下一个节点
        dump.next = head;
        //pre为每次翻转的头结点
        ListNode pre = dump;
        //每次翻转的尾巴节点
        ListNode end = dump;
        while (end.next != null) {
            //遍历K个几点
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
            //头结点
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
     * 翻转链表
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        //单个节点不需要饭庄
        if (head == null || head.next == null) {
            return head;
        }
        //当前节点的前一个节点
        ListNode pre = null;
        //当前节点
        ListNode cur = head;
        //开始翻转
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
