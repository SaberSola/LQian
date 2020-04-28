package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-28
 * @Des ${todo}
 */
public class Solution14 {


    /**
     * 快慢指针
     * 快节点先走N步
     * 慢节点继续走
     * @param head
     * @param n
     * @return
     * 0 1 2 3 4
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode fast = pre, slow = pre;
        for(int i=0;i<n+1;i++){//快指针先移动N+1个位置
            fast=fast.next;
        }
        while (fast !=  null){
            fast = fast.next;
            slow = slow.next;
        }
        //当前节点就是倒数第n个节点
        slow.next = slow.next.next;
        return pre.next;
    }

}
