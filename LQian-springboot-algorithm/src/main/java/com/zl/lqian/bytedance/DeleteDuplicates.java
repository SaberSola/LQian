package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;

public class DeleteDuplicates {

    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 返回同样按升序排列的结果链表。
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        //定义虚拟头结点
        ListNode dummy = new ListNode(0);
        //尾插
        ListNode tail = dummy;
        // 1-->2-->2-->3-->4
        // 1-->3-->4
        for (ListNode l = head, r = head; l != null; l = r) {
            //相同节点跳过
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

    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(3);
        ListNode h4 = new ListNode(3);
        ListNode h5 = new ListNode(4);
        ListNode h6 = new ListNode(4);
        ListNode h7 = new ListNode(5);
        h1.next = h2;
        h2.next = h3;
        h3.next = h4;
        h4.next = h5;
        h5.next = h6;
        h6.next = h7;
        deleteDuplicates(h1);
    }

    /**
     * 删除连接重复列表
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode dummy = head;
        while (dummy.next != null){
            if (dummy.val ==dummy.next.val ){
                dummy.next = dummy.next.next;
            }else {
                dummy = dummy.next;
            }
        }
        return head;
    }
}
