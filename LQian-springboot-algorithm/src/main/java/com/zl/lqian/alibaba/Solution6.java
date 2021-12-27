package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution6 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        after(root, res);
        return res;
    }

    /**
     * @param root
     * @param result
     */
    private void after(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        after(root.getLeft(), result);
        after(root.getRight(), result);
        result.add(root.getVal());
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {//将已经弹出根节点放入占中
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
