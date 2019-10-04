package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-30
 * @Des  输出该链表的倒数第K个结点
 */
public class FindKthToTail {


    /**
     * 一快一慢双指针
     * @param head
     * @param k
     * @return
     */
    public static Common.ListNode findKthToTail(Common.ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        Common.ListNode fast = head;
        Common.ListNode slow = head;
        while (k-- > 1) {
            if (fast.next == null) {
                return null;
            }
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }



}
