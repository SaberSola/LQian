package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-15
 * @Des ${todo}
 */
public class Solution45 {


    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }


    /**
     * 5
     * 4      8
     *
     * @param node  1   3  6   6
     * @param left
     * @param right
     * @return
     */
    private boolean helper(TreeNode node, Integer left, Integer right) {
        if (node == null) {
            return true;
        }
        if (left != null && node.val <= left) return false;
        if (right != null && node.val >= right) return false;
        if (!helper(node.right, node.val, right)) return false; //判断右子树
        if (!helper(node.left, left, node.val)) return false;   //判断左子树
        return true;
    }
}
