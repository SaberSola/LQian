package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Fatten {
    /**
     * 二叉树转链表
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        //需要连接器
        for (int i = 1; i < list.size(); i++) {
            //获取前一个节点
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    /**
     * 前序
     *
     * @param root
     * @param list
     */
    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }
}
