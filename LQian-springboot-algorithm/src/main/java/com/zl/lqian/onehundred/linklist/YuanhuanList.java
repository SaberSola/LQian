package com.zl.lqian.onehundred.linklist;

import com.zl.lqian.leetcode.ListNode;

public class YuanhuanList {


    /**
     * 判断指针是否又换
     *
     * @param head
     * @return
     */
    public Boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;

    }
}
