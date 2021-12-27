package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 112. 路径总和
 */
public class HasPathSum {

    /**
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        /**
         * 思路就是深度优先搜搜
         */
        if (root == null) {
            return false;
        }
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode root, int num) {
        //递归的边界 节点为null
        if (root.left == null && root.right == null){
            return (num - root.val) == 0;
        }
        //网左子树搜搜
        if (root.left != null && dfs(root.left,num - root.val)){
            return true;
        }

        //往右子树搜索
        if (root.right != null && dfs(root.right,num - root.val)){
            return true;
        }
        return false;
    }
}
