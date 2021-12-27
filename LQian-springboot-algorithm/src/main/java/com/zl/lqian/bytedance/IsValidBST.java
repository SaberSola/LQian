package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 98. 验证二叉搜索树
 */
public class IsValidBST {

    long pre = Long.MIN_VALUE; // 记录上一个节点的值，初始值为Long的最小值

    /**
     *
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }


    private boolean inorder(TreeNode node) {
        if(node == null) {return true;}
        boolean l = inorder(node.left);
        if(node.val <= pre) {return false;}
        pre = node.val;
        boolean r = inorder(node.right);
        return l && r;
    }
}
