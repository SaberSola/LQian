package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.ListNode;
import com.zl.lqian.leetcode.TreeNode;

public class IsSubPath {


    /**
     * 1367. 二叉树中的列表
     * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
     *
     * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
     *
     * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/linked-list-in-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        //先判断当前的节点，如果不对，再看左子树和右子树呗
        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode node) {
        //特判：链表走完了，返回true
        if (head == null) {
            return true;
        }
        //特判：链表没走完，树走完了，这肯定不行，返回false
        if (node == null) {
            return false;
        }
        //如果值不同，必定不是啊
        if (head.val != node.val) {
            return false;
        }
        //如果值相同，继续看，左边和右边有一个满足即可
        return isSub(head.next, node.left) || isSub(head.next, node.right);
    }
}
