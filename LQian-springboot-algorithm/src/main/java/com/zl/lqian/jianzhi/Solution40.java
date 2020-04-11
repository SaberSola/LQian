package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-10
 * @Des 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class Solution40 {


    public boolean IsBalanced_Solution(Common.TreeNode root) {
        if (root == null){
            return true;
        }
        int left = TreeDepth(root.right);
        int right = TreeDepth(root.left);
        int diff = Math.abs(left-right);
        if (diff > 1){
            return false;
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }


    public int TreeDepth(Common.TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return Math.max(left,right) + 1;
    }

}
