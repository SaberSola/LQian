package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 101. 对称二叉树
 */
public class IsSymmetric {


    /**
     * 判断是否是镜像二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        /**
         * 判断是不是镜像的是,只需要判断 左树的左孩子和右树的右孩子  左树的右孩子和右树的做孩子 是否相等
         */
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        //判断孩子是否相同
        if (t1.val == t2.val) {
            return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
        }
        return false;
    }

}
