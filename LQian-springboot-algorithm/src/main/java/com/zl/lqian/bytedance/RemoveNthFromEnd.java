package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

/**
 * 移除链表的第N个节点
 */
public class RemoveNthFromEnd {

    /**
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         *
         * 快慢指针,快指针走步,慢指针开始走
         *
         */
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode fast = pre;
        ListNode slow = pre;
       for (int i = 0 ; i < n+1; i ++){
           fast = fast.next;
       }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return pre.next;
    }

}
