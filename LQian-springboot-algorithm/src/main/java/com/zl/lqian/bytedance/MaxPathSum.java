package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 *
 *
 * 难度过大的题,暂时不做

 */
public class MaxPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null){
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        maxSum = Math.max(maxSum,priceNewpath);
        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

}
