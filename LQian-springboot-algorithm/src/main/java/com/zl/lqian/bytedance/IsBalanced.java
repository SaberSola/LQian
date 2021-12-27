package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.right) && isBalanced(root.left);
    }


    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.right), height(root.left)) + 1;
        }

    }
}
