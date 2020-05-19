package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-19
 * @Des ${todo}
 */
public class Solution54 {


    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 前序遍历是 根左右
     * 中序遍历是 左根右
     *
     * 遍历中序遍历指导找到和根节点相同的位置,此元素左右都是左子树的元素,右方全是右子树的元素
     * 通过递归求解
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
            return null;
        }

    }

    private TreeNode help(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        //递归退出条件
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        //重建根节点
        TreeNode treeNode = new TreeNode(preorder[pStart]);
        int index = 0;//找到根节点在中序遍历的位置
        while (inorder[pStart + index] != preorder[pStart]){
            index ++;
        }
        //重建

    }


}
