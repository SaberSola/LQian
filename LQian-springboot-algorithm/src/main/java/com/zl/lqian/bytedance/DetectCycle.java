package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;

/**
 * 环形链表 二
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {

        /**
         *  判断一个链表是否有环
         *  快慢指针 如果两个节点相交,则就是说明有环
         */
        //先判断是否有环
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || slow == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //此时两个节点是相交的 slow == fast
        //头结点出发,最终会在还的入口相遇
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;

    }
}
