package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class GetKthFromEnd {

    /**
     * 链表的第K个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head, latter = head;
        for (int i = 0; i < k; i++)
            former = former.next;
        while (former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
}
