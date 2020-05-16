package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-15
 * @Des ${todo}
 */
public class Solution46 {


    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    /**
     * 判断左右子树的节点是否想用
     */
    public boolean isMirror(TreeNode t1, TreeNode t2) {

        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

}
