package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 剑指 Offer 27. 二叉树的镜像
 */
public class MirrorTree {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null){
            return null;
        }
        //开始交换
        TreeNode temp = root.left;
        root.left =root.right;
        root.right = temp;
        //继续交换
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
