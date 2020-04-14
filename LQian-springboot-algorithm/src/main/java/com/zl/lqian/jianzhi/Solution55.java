package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-12
 * @Des ${todo}
 */
public class Solution55 {


    /**
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。
     * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     *
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(Common.TreeNode pRoot) {
        if (pRoot == null){
            return true;
        }
        return juge(pRoot.left,pRoot.right);
    }

    public boolean juge(Common.TreeNode left, Common.TreeNode right){

        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val){
            return false;
        }else {
            return juge(left.left, right.right) && juge(right.left, left.right);
        }
    }

}
