package com.zl.lqian.jianzhioffer;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2019-09-27
 * @Des 前序遍历 中序遍历 还原二叉树
 */
public class BinaryTreeSearch {


    /**
     * @param pre 前序遍历 数组
     * @param in  中序遍历 数组
     * 前序遍历 root left right
     * 中序遍历 left root right
     *
     * 前序遍历序列：1, 2, 4, 8, 16
     *
     * 中序遍历序列：2, 1, 8, 4, 16
     *
     *     1
     *   /   \
     *  2     4
     *       / \
     *      8  16
     *
     * @return
     */
    public static Common.TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        if (pre.length != in.length) {
            return null;
        }
        Common.TreeNode root = new Common.TreeNode(pre[0]); //前序遍历的第一个节点就是根节点
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }

        return root;
    }


}
