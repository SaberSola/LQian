package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.ArrayList;

/**
 * @Author zl
 * @Date 2020-04-07
 * @Des ${todo}
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径
 * 。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class Solution24 {


    /**
     * 遍历几点 深度优先
     *
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(Common.TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        find(root, 0, target, new ArrayList(), result);
        return result;

    }


    public void find(Common.TreeNode node, int sum, int target, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> result) {
        //结束递归
        if (node == null && sum == target && !result.contains(list)) {
            result.add(list);
            return;
        }
        if (node == null) {
            return;
        }
        sum += node.val;
        list.add(node.val);
        if (sum > target) {
            return;
        }
        find(node.left, sum, target, new ArrayList<>(list), result);
        find(node.right, sum, target, new ArrayList<>(list), result);
    }

    public ArrayList<ArrayList<Integer>> FindPath1(Common.TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        find1(root, 0, target, new ArrayList(), result);
        return result;

    }

    public void find1(Common.TreeNode node, int sum, int target, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> result) {
        //结束递归的标志
        if (node == null && sum == target && !result.contains(list)) {
            result.add(list);
        }
        if (node == null){
            return;
        }
        sum += node.val;
        list.add(node.val);
        if (sum > target){
            return;
        }

        find(node.left, sum, target, new ArrayList<>(list), result);
        find(node.right, sum, target, new ArrayList<>(list), result);

    }

}
