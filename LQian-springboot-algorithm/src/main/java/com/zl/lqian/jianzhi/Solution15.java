package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des ${todo}
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class Solution15 {


    /**
     * 1 --> 2  --> 3  --> 4  -->  5
     * <p>
     * 5 --> 4  --> 3  --> 2  -->  1
     *
     * @param args
     */
    public static void main(String[] args) {


    }

    /**
     * 1 --> 2  --> 3  --> 4  -->  5
     * <p>
     * 5 --> 4  --> 3  --> 2  -->  1
     *
     * @param args
     */
    public Common.ListNode ReverseList(Common.ListNode head) {

        //初始化pre指针，用于记录当前结点的前一个结点地址
        Common.ListNode pre = null;
        //初始化p指针，用于记录当前结点的下一个结点地址
        Common.ListNode p = null;

        /**
         *
         *
         *
         *    1 --> 2 --> 3
         *
         *   pre  p;
         *   p = 2
         *   2 = pre
         *   pre = 1
         *   head = 2
         *
         *
         *          1 ---> 2  -----> 3  ----->  4
         *
         *          1<---- 2 -----> 3  ----->  4
         */
        while (head == null) {
            //先用p指针记录当前结点的下一个结点地址。
            p = head.next;
            //让被当前结点与链表断开并指向前一个结点pre。
            head.next = pre;
            //pre指针指向当前结点
            pre = head;
            //head指向p(保存着原链表中head的下一个结点地址)
            head = p;
        }
        return pre;//当循环结束时,pre所指的就是反转链表的头结点
    }


    /**
     * 反转链表           1 ----> 2 -----> 3 ----->4
     * 1 <---- 2 ------>3 -------5;
     * <p>
     * //需要保存下的前一个节点
     *
     * @param head
     * @return
     */
    public Common.ListNode ReverseList2(Common.ListNode head) {
        //记录当前指针的前一个节点
        Common.ListNode pre = null;
        Common.ListNode p = null;//当前节点的下一个节点

        while (head != null) {
            p = head.next;
            head.next = pre; //next只想他的前一个指针
            pre = head;
            head = p;
        }
        return pre;
    }

    /**
     * @param head
     * @return
     */
    public Common.ListNode ReverseList3(Common.ListNode head) {

        /**
         *      1 -----> 2 ----> 3 ----> 4
         *
         *
         *      1 <----- 2 ----> 3 -----> 4
         *
         */
        //当前指针的前驱节点
        Common.ListNode pre = null;
        //当前指针的下一个节点
        Common.ListNode curNext = null;
        while (head != null) {
            //head是当前节点
            curNext = head.next;
            //开始swap交换操作
            head.next = pre;
            pre = head;
            head = curNext;
        }
        return pre;
    }
}


