package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author zl
 * @Date 2020-05-19
 * @Des ${todo}
 */
public class Solutio52 {


    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回锯齿形层次遍历如下：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     * //使用俩个栈交替存储
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //栈1来存储右节点到左节点的顺序
        Stack<TreeNode> stack1 = new Stack<>();
        //栈2来存储从左节点到右节点的顺序
        Stack<TreeNode> stack2 = new Stack<>();
        //根节点入栈
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> subList = new ArrayList<>(); // 存储这一个层的数据
            TreeNode cur = null;
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    cur = stack1.pop();
                    subList.add(cur.val);
                    if (cur.left != null) {
                        stack2.push(cur.left);
                    }
                    if (cur.right != null) {
                        stack2.push(cur.right);
                    }
                }
                list.add(subList);
            } else{
                while (!stack2.isEmpty()){
                    cur = stack2.pop();
                    subList.add(cur.val);
                    if (cur.right != null) {
                        stack1.push(cur.right);
                    }
                    if (cur.left != null) {
                        stack1.push(cur.left);
                    }
                }
                list.add(subList);
            }
        }

        return list;
    }

}
