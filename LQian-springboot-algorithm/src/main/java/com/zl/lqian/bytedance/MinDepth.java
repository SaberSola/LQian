package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 111. 二叉树的最小深度
 */
public class MinDepth {

    /**
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明：叶子节点是指没有子节点的节点。
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        /**
         * 首先可以想到使用深度优先搜索的方法，遍历整棵树，记录最小深度。
         *
         * 对于每一个非叶子节点，我们只需要分别计算其左右子树的最小叶子节点深度。这样就将一个大问题转化为了小问题，可以递归地解决该问题。
         *
         *深度有限搜索
         */
        if (root == null) {
            return 0;
        }
        //搜索左子树
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }
}
