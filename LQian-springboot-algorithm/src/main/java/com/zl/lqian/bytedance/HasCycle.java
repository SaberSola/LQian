package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class HasCycle {

    /**
     * 环形链表
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        //唤醒链表一般是快慢指针
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
    }

}
