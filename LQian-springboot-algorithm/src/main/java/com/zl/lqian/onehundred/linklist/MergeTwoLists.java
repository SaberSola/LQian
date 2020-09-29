package com.zl.lqian.onehundred.linklist;

import com.zl.lqian.leetcode.ListNode;

public class MergeTwoLists {


    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode head = new ListNode(0);
        ListNode pre = head;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            } else
            {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return head.next;
    }

}
