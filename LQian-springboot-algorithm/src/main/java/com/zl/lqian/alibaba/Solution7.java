package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 */
public class Solution7 {


    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            //层数节点数
            List<Integer> level = new ArrayList<Integer>();
            //队列数量
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i ++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        return ret;
    }
    /**
     *                    1
     *                2       3
     *             4     5  6    7
     *
     * 1  queue: 2 ,3    level 1
     * 2  queue 4 5 6 7  level 2 ,3
     *
     *
     *
     *
     *
     *
     */

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        before(root,res);
        return res;
    }


    private void before(TreeNode root,List<Integer> res){
        if (root == null) {return;}
        res.add(root.val);
        before(root.left,res);
        before(root.right,res);
    }

}
