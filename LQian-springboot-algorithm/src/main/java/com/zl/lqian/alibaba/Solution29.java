package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution29 {

    /**
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例1:
     *
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     *
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null){
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null){
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        /**
         * 我们利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。
         */
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}
