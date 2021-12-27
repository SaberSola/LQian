package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution25 {

    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤m≤n≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) {
            return head;
        }
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            //所有元素放入集合
            list.add(cur.val);
            cur = cur.next;
        }
        //注意索引要减一，因为集合从0开始
        int i = m - 1;
        int j = n - 1;
        while (i < j){
            //交换位置
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
            i++;
            j--;
        }
        ListNode dumy = new ListNode(0);
        ListNode res = dumy;
        for (int k = 0; k < list.size(); k++) {
            //重新串节点
            dumy.next = new ListNode(list.get(k));
            dumy = dumy.next;
        }
        return res.next;
    }
}
