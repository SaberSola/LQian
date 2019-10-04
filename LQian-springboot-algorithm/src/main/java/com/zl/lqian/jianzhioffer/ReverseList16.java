package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-10-04
 * @Des 翻转链表
 */
public class ReverseList16 {


    public static Common.ListNode reverseList3(Common.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        Common.ListNode p = reverseList3(head.next);
        head.next.next = head.next;
        head.next = null;
        return p;
    }

}
