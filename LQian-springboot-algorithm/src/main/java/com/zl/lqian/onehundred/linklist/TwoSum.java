package com.zl.lqian.onehundred.linklist;

import com.zl.lqian.leetcode.ListNode;

public class TwoSum {

    public ListNode twoSum(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0); //dummyHead value什么值无所谓
        ListNode p = l1,q =l2,curr = dummyHead;
        int carry = 0;//进位
        while (p != null || q!= null || carry != 0){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);//处理进位
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;

        }
        return dummyHead.next;
    }


}

