package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class AddTwoNumbers {


    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return result.next;

    }

    /**
     * 445. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumberss(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode newHead = new ListNode(0);
        ListNode ptr = newHead;
        while (l1 != null && l2 != null) {
            ptr.val = l1.val + l2.val + ptr.val;
            if (ptr.val > 9) {
                ptr.val -= 10;
                ptr.next = new ListNode(1);
            } else {
                ptr.next = new ListNode(0);
            }
            ptr = ptr.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            ptr.val = ptr.val + l1.val;
            if (ptr.val > 9) {
                ptr.val -= 10;
                ptr.next = new ListNode(1);
            } else {
                ptr.next = new ListNode(0);
            }
            ptr = ptr.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            ptr.val = ptr.val + l2.val;
            if (ptr.val > 9) {
                ptr.val -= 10;
                ptr.next = new ListNode(1);
            } else {
                ptr.next = new ListNode(0);
            }
            ptr = ptr.next;
            l2 = l2.next;
        }
        ListNode res = reverse(newHead);
        return res.val == 0 ? res.next : res;

    }

    // 反转链表
    public ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}

