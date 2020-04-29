package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-29
 * @Des ${todo}
 */
public class Solution17 {


    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 给定 1->2->3->4, 你应该返回
     * 2->1->4->3.
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        //递归法 最小单元
        if (head == null || head.next == null){
            return null;
        }
        //需要交换位置的节点
        //设需要交换的两个点为 head 和 next，head 连接后面交换完成的子链表，next 连接 head，完成交换
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
