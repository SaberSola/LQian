package com.zl.lqian.alibaba;

import com.zl.lqian.cs.algs4.Stack;
import com.zl.lqian.leetcode.TreeNode;

/**
 * @author zhanglei
 */
public class Solution40 {

    /**
     * 输出二叉树的镜像
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            } ;
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
}
