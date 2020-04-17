package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-09
 * @Des 输入两个链表，找出它们的第一个公共结点。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 */
public class Solution37 {

    /**
     * 输入两个链表，找出它们的第一个公共结点。
     * 双指针法
     *
     *   1  2  3  4  6 7 8
     *     10 11 23  6 7 8
     * p1走 7到8
     * p2走 7到1
     *
     * p1到10
     * p2到2 第二次相当于走了个俩个链表之差
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public Common.ListNode FindFirstCommonNode(Common.ListNode pHead1, Common.ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        Common.ListNode p1 = pHead1;
        Common.ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = p1 == null ? pHead2 : p1.next;
            p2 = p2 == null ? pHead1 : p2.next;
        }
        return p1;
    }

    public Common.ListNode FindFirstCommonNode1(Common.ListNode pHead1, Common.ListNode pHead2) {

        if (pHead1 == null || pHead2 == null) return null;
        Common.ListNode p1 = pHead1;
        Common.ListNode p2 = pHead2;
        while (p1 != p2){
            p1 = p1 == null?pHead2:p1.next;
            p2 = p2 == null?pHead1:p2.next;
        }

        return p1;
    }

}
