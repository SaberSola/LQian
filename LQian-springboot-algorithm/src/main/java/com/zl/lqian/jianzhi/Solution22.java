package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author zl
 * @Date 2020-04-07
 * @Des ${todo}
 */
public class Solution22 {


    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * 按照层打印
     *                       1
     *                    2      3
     *                  4   5  6   7
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(Common.TreeNode root) {
        //使用队列
        ArrayList<Integer> list = new ArrayList<>();

        Queue<Common.TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            Common.TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right!= null) queue.offer(node.right);

        }
        return list;
    }

    /**
     * 使用队列
     * 每次根节点出队列就把 左右节点放入队列
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom2(Common.TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Common.TreeNode> queue = new LinkedList();
        queue.offer(root);//放入根节点
        while (!queue.isEmpty()){
            Common.TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return list;

    }





    }
