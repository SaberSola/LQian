package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 */
public class KthSmallest {
    int count = 0;
    int ans = 0;

    /**
     * 二叉树中第K小的元素,中序遍历是顺序遍历,找到第k个就行
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    private void inorder(TreeNode root, int k) {

        if (root == null) {
            return;
        }
        inorder(root.left, k);
        count++;
        if (count == k) {
            ans = root.val;
        } else {
            inorder(root.right, k);
        }

    }
}
