package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.TreeNode;

public class Solution12 {

    int ans = 1;
    /**
     * 二叉树的直径
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        deepTree(root);
        return ans - 1;
    }

    private int deepTree(TreeNode root) {
        if (root == null) {
            //空节点,返回0
            return 0;
        }
        int L = deepTree(root.left);
        int R = deepTree(root.right);
        ans = Math.max(L + R + 1, ans);
        return Math.max(L, R) + 1;
    }


}
