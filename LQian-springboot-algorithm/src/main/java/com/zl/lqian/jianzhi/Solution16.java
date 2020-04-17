package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.List;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 */
public class Solution16 {


    public static void main(String[] args) {

    }


    public Common.ListNode Merge(Common.ListNode list1, Common.ListNode list2) {
        //建立虚拟头结点
        Common.ListNode dump = new Common.ListNode(0);
        Common.ListNode cur = dump;
        while (list1 != null && list2 != null){
            //开始比较大小
            if (list1.val > list2.val){
                //当前指针连接比较小的节点
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }else {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            }
        }

        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;

        return dump.next;
    }


    /**
     * 单调递增的俩个俩表合成一个链表
     * @param list1
     * @param list2
     * @return
     */
    public Common.ListNode Merge2(Common.ListNode list1, Common.ListNode list2) {
        //虚拟节点
        Common.ListNode dump = new Common.ListNode(0);
        Common.ListNode cur = dump;
        while (list1 != null && list2 != null){
            if (list1.val > list2.val){
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }else {
                cur.next = list1;
                cur = cur.next;
                list1 = list2.next;
            }
        }
        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;
        return dump.next;

    }









}
