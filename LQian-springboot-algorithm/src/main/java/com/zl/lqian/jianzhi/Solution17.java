package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 */
public class Solution17 {


    /**
     *
     * root2
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(Common.TreeNode root1, Common.TreeNode root2) {
        if (root1 == null || root2 == null){
            return false;
        }
        if (root1.val == root2.val && isContain(root1, root2)){
            return true;
        }
        return HasSubtree(root1.left,root2) || HasSubtree(root1.right, root2);
    }

    public boolean isContain(Common.TreeNode node1, Common.TreeNode node2){
        if (node1 == null && node2 != null){
            return false;
        }
        if (node2 == null){
            return true;
        }

        return node2.val == node1.val & isContain(node1.left, node2.left)&isContain(node1.right, node2.right);

    }
}
