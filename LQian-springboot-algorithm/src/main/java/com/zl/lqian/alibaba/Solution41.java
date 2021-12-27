package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.ListNode;

/**
 * @author zhanglei
 */
public class Solution41 {


    /**
     * 链表中倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        /**
         * 双指针法 快指针走k,慢指针开始走
         *
         */
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
