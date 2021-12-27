package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

/**
 * 92. 反转链表 II
 */
public class ReverseBetween202109017 {
    /**
     * 翻转链表
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        /**
         * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
         *
         * head = [1,2,3,4,5], left = 2, right = 4
         *    1 ---- 2 ---- 3 ---- 4 ----5
         *
         *    翻转后
         *
         *    1 --- 4 ------3 ----- 2 ----5
         *
         *   1、我们定义两个指针，分别称之为 g(guard 守卫) 和 p(point)。
         *
         *   2 将g移动到第一个翻转链表的头结点前边,将p移动到第一个要翻转的节点上
         *
         *    1 ---- 2 ---- 3 ---- 4 ----5
         *    g      p
         *
         *   3.将p后边的节点删除,添加到 g 后边 头插法,然后循环
         *
         *
         */
        // 定义一个虚拟头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        for (int i = 0; i < m - 1; i++) {
            g = g.next;
            p = p.next;
        }
        //此时g就是翻转节点的前一个节点,p就翻转节点的第一个节点
        for (int i = 0; i < n - m; i++) {
            //删除P后边的节点
            ListNode temp = p.next;
            p.next = p.next.next;
            temp.next = g.next;
            g.next = temp;
        }
        return dummyHead.next;
    }
}
