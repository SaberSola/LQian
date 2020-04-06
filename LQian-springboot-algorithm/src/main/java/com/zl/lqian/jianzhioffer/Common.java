package com.zl.lqian.jianzhioffer;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author zl
 * @Date 2019-09-27
 * @Des ${todo}
 */
public class Common {

    /**
     * 二叉树
     */
    @Setter
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 链表
     */
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 获取链表的所有节点
     *
     * @param head
     * @return
     */
    public static String getAllListNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" → ");
            head = head.next;
        }
        return sb.length() < 1 ? null : sb.substring(0, sb.length() - 2);
    }

}
