package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-19
 * @Des ${todo}
 */
public class Solution53 {


    /**
     * 树的最大高度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }


}
