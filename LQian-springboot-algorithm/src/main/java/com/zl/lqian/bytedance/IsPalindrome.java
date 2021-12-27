package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 234. 回文链表
 */
public class IsPalindrome {

    /**
     * 判断是否是回文链表.第一步是需要找到中间节点,并且翻转前边节点,然后依次判断值是否相同
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //找到中节点
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //此时slow就是前半部分；
        //翻转后半部分
        ListNode pre = null;
        ListNode cur = slow.next;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        //此时的pre就是后半部分的头结点
        while (pre != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        return true;
    }

    /**
     * 双指针
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
