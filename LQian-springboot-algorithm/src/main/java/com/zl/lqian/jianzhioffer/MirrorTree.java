package com.zl.lqian.jianzhioffer;

import java.util.Stack;

/**
 * @Author zl
 * @Date 2019-10-04
 * @Des 二叉树的镜像
 */

public class MirrorTree {


    /**
     * 二叉树的镜像
     * 递归
     *
     * @param root
     */
    public void Mirror(Common.TreeNode root) {
        if (root == null) {
            return;
        }

        Common.TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    /**
     * 非递归
     *
     * @param root
     */
    public void Mirror2(Common.TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<Common.TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                Common.TreeNode temp = root.left;
                root.left = root.right;
                root.right = temp;
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }

    }

}
