package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 */
public class RightSideView {

    /**
     * 二叉树的右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        //思路应该是层序遍历
        List<Integer> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        //添加根节点,也就是第一层
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //当前层的最后一个节点
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    resList.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
        }
        return resList;
    }
}
