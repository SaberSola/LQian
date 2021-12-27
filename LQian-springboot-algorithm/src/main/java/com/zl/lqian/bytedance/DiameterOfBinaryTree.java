package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 543. 二叉树的直径二叉树的直径,左子树左节点,到右子树的右节点的节点数 -1
 */
public class DiameterOfBinaryTree {
    int ans = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        deepTree(root);
        return ans - 1;
    }

    private int deepTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //继续搜索左子树
        int L = deepTree(root.left);
        int R = deepTree(root.right);
        ans = Math.max(L + R + 1, ans);
        return Math.max(L, R) + 1;
    }
}
