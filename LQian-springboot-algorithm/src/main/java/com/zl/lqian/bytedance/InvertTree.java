package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

public class InvertTree {

    /**
     *  二叉树的镜像
      * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        //交换当前节点
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //递归交换当前节点的 左子树
        invertTree(root.left);
        //递归交换当前节点的 右子树
        invertTree(root.right);
        //函数返回时就表示当前这个节点，以及它的左右子树
        //都已经交换完了
        return root;
    }

}
