package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 第K大的节点
 */
public class KLargest {


    int res;
    int index = 0; //计数器

    public int kthLargest(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.right, k);
        index++;
        if (k == index) {
            res = root.val;
        }
        dfs(root.left, k);
    }
}





