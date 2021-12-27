package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

/**
 * k个一组翻转链表
 */
public class Solution2 {

    /**
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }
        // 定义虚拟头结点
        ListNode dummy = new ListNode(0);
        //虚拟节点指向head
        dummy.next = head;
        //初始化 pre和end节点
        //pre是翻转链表的头结点的上一个节点
        ListNode pre = dummy;
        //翻转链表的最后一个节点
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++){
                end=end.next;
            }
            //如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
            if (end == null){
                break;
            }
            //记录下 end.next
            ListNode next = end.next;
            //断开当前链表,链表已经断开
            end.next = null;
            //翻转链表的头结点
            ListNode start = pre.next;
            //开始翻转列表
            pre.next=reverse(start);
            //翻转后头节点变到最后。通过.next把断开的链表重新链接。
            start.next=next;
            //将pre转为下次翻转列表的的头一个节点
            pre = start;
            //翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end=start;
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null){
            return head;
        }
        //前一个节点指针
        ListNode preNode = null;
        //当前节点指针
        ListNode curNode = head;
        //下一个节点指针
        ListNode nextNode = null;
        while (curNode != null){
            nextNode = curNode.next;//nextNode 指向下一个节点,保存当前节点后面的链表。
            curNode.next=preNode;//将当前节点next域指向前一个节点   null<-1<-2<-3<-4
            preNode = curNode;//preNode 指针向后移动。preNode指向当前节点。
            curNode = nextNode;//curNode指针向后移动。下一个节点变成当前节点
        }
        return preNode;
    }
}
