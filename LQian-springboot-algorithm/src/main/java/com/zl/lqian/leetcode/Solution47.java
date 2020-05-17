package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author zl
 * @Date 2020-05-16
 * @Des ${todo}
 */
public class Solution47 {


    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * //使用队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                level.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if (!level.isEmpty()){
                res.add(level);
            }
        }
        return res;
    }
}
