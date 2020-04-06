package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des ${todo}
 *  输入一个链表，输出该链表中倒数第k个结点。
 */
public class Solution14 {


    /**
     * 快慢节
     * 快指针走k 慢指针开始,
     * @param head
     * @param k
     * @return
     */
    public Common.ListNode FindKthToTail(Common.ListNode head, int k) {
        Common.ListNode  fast = head;
        Common.ListNode  slow = head;
        for (int i=0; i<k; i ++){
            if (fast == null){
                return null;
            }
            fast = fast.next;
        }
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


}
