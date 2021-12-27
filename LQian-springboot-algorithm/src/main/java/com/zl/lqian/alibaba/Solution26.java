package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.TreeNode;

public class Solution26 {


    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }else {
            //判断左子树,右子树
            return Math.abs(height(root.left) - height(root.right)) <= 1 && (isBalanced(root.getRight()) && isBalanced(root.getLeft()));
        }
    }

    public int height(TreeNode root) {
        if (root == null){
            return 0;
        }else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
}
