package com.zl.lqian.jianzhioffer;

import java.util.ArrayList;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des 输入一个二叉树和一个整数
 * 打印出二叉树中节点值的和为输入整数的所有路径
 */
public class FindPath {


    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<>();


    /**
     * 寻找和为target的的路径
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(Common.TreeNode root, int target) {
        if (root == null){
            return listAll;
        }
        list.add(root.val);
        target -= root.val;

        if (target == 0 && root.left == null && root.right == null) {
            listAll.add(new ArrayList<>(list));
        }

        FindPath(root.left, target);
        FindPath(root.right, target);

        list.remove(list.size() - 1);

        return listAll;
    }

}
