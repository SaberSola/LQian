package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-06-14
 */
public class Solution68 {


    /**
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null && headB == null) return null;
        ListNode pA = headA, pB = headB;
       while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
