package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.ListNode;

public class Solution24 {


    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
     *
     * 示例1:
     *
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例2:
     *
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        //虚拟头结点
        ListNode dummy = new ListNode(-1);
        //定义一个节点 尾插法
        ListNode tail = dummy;
        /**
         *   1->2->3->3->4->4->5
         *
         *   第一次循环:  l = 1 ,r = 1
         *              l = 1 ,r = 2
         *   tail:      1
         *   第二次循环   l = 2 , r = 2
         *              l = 2 , r = 3
         *   tail:      2
         */
        for (ListNode l = head, r = head; l != null; l = r) {
            //只要r不为空,并且r == l,则一直移动
            while (r != null && r.val == l.val) {
                r = r.next;
            }
            if (l.next == r) {
                //长度为1
                tail.next = l;
                tail = l;
                tail.next = null;
            }
        }
        return dummy.next;
    }

}
