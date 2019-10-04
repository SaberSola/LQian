package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-10-04
 * @Des 输入两个递增的链表，合并这两个链表并使新链表仍然是递增的
 */
public class MergeLinkList {


    public static Common.ListNode mergeTwoLists2(Common.ListNode list1, Common.ListNode list2) {
        Common.ListNode preHead = new Common.ListNode(-1);
        Common.ListNode pre = preHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }

        pre.next = list1 == null ? list2 : list1;
        return preHead.next;

    }


}
