package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 二叉树最近的公共祖先
 */
public class LowestCommonAncestor {


    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /**
         *   总共三种情况
         *   1: p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中)
         *   2: p =root ，且 q 在 root 的左或右子树中；
         *   3: q=root ，且 p 在 root 的左或右子树中
         *
         *   1: 递归的终止条件
         *      1. 当越过叶子节点,直接返回null
         *      2. 当root等于p,q直接返回root
         *   2: 递归工作
         *      1. 开始递归左节点,返回值为left
         *      2. 递归右节点,返回值为left
         *   3: 返回值
         *      1. 当 left 和 right 同时为空 ：说明 rootroot 的左 / 右子树中都不包含 p,q ，返回null
         *      2. 当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 两侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
         *      3. 当 left 为空 ，right 不为空 ：p,q 都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
         *          1. p,q 其中一个在 root 的 右子树 中，此时 right 指向 p（假设为 p ）；
         *          2. p,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
         *      4. 当 leftl不为空 ， right为空 ：与情况 3. 同理；
         *
         */

        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        // 1.
        if (left == null) {
            return right;
        } // 3.
        if (right == null) {
            return left;
        }// 4.

        return root; // 2. if(left != null and right != null)
    }

}

