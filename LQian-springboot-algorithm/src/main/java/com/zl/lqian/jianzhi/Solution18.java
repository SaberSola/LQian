package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des ${todo}
 * 求二叉树的镜像
 */
public class Solution18 {



    public void Mirror(Common.TreeNode root) {
        if (root == null){
            return;
        }
        //左右子树交换节点
        Common.TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        temp = null;
        Mirror(root.left);
        Mirror(root.right);
    }

}
