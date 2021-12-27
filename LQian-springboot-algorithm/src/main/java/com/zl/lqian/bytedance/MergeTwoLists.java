package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class MergeTwoLists {

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //定义一个虚拟头结点
        ListNode dump = new ListNode(0);
        //这个节点是需要跟着迭代走的
        ListNode prev = dump;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return dump.next;
    }
}
