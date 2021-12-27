package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

import java.util.*;

/**
 * 之字形打印二叉树
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        //创建队列，保存节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);//先把节点加入到队列中
        boolean leftToRight = true;//第一步先从左边开始打印
        while (!queue.isEmpty()) {
            //记录每层节点的值
            List<Integer> level = new ArrayList<>();
            //统计这一层有多少个节点
            int count = queue.size();
            //遍历这一层的所有节点，把他们全部从队列中移出来，顺便
            //把他们的值加入到集合level中，接着再把他们的子节点（如果有）
            //加入到队列中
            for (int i = 0; i < count; i++) {
                //poll移除队列头部元素（队列在头部移除，尾部添加）
                TreeNode node = queue.poll();
                //判断是从左往右打印还是从右往左打印。
                if (leftToRight) {
                    //如果从左边打印，直接把访问的节点值加入到列表level的末尾即可
                    level.add(node.val);
                } else {
                    //如果是从右边开始打印，每次要把访问的节点值
                    //加入到列表的最前面
                    level.add(0, node.val);
                }
                //左右子节点如果不为空会被加入到队列中
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            //把这一层的节点值加入到集合res中
            res.add(level);
            //改变下次访问的方向
            leftToRight = !leftToRight;
        }
        return res;
    }

}
