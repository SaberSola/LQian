package com.zl.lqian.jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des 层序遍历二叉树
 */
public class PrintFromTopToBottom {


    /**
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(Common.TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Queue<Common.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Common.TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return list;
    }


    public ArrayList<Integer> PrintFromTopToBottom2(Common.TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        list.add(root.val);
        levelOrder(root,list);
        return list;
    }


    public void levelOrder(Common.TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            list.add(root.left.val);
        }
        if (root.right != null) {
            list.add(root.right.val);
        }
        levelOrder(root.left, list);
        levelOrder(root.right, list);
    }


}
