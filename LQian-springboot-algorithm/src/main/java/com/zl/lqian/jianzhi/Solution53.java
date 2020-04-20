package com.zl.lqian.jianzhi;

import com.zl.lqian.cs.algs4.In;
import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-12
 * @Des ${todo}
 */
public class Solution53 {


    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     * 快慢制作呢
     *
     *  1 2 2 3
     *  1 2 2 3 3 4
     * 双指针
     * @param pHead
     * @return
     */
    public Common.ListNode deleteDuplication(Common.ListNode pHead) {
        Common.ListNode root = new Common.ListNode(Integer.MAX_VALUE);
        root.next = pHead;
        Common.ListNode pre = root;
        Common.ListNode cur = root;
        while (cur != null){
            while (cur.next != null && cur.val == cur.next.val){//重复节点
                cur = cur.next;
            }
            //非重复节点
            cur = cur.next;
            if(cur != null && cur.next != null && cur.val == cur.next.val){
                //连续重复的节点
                continue;
            }
            pre.next = cur;
            pre = pre.next;
        }

        return root.next;
    }


    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     * 快慢制作呢
     *
     *  1 2 2 3
     *  1 2 2 3 3 4
     * 双指针
     * @param pHead
     * @return
     */
    public Common.ListNode deleteDuplication2(Common.ListNode pHead) {
        Common.ListNode root = new Common.ListNode(Integer.MAX_VALUE);
        root.next = pHead;
        //双指针
        Common.ListNode pre = root;
        Common.ListNode cur = root;
        while (cur != null){
            while (cur.next != null && cur.val == cur.next.val){//重复节点
                cur = cur.next;
            }
            cur = cur.next;
            //解决连续重复的节点
            if(cur != null && cur.next != null && cur.val == cur.next.val){
                //连续重复的节点
                continue;
            }
            pre.next = cur;
            pre = pre.next;
        }
        return root.next;
    }


}
