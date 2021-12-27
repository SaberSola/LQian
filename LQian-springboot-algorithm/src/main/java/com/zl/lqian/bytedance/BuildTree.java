package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {


    /**
     * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /**
         * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
         *
         *                   3
         *               9       20
         *                    15    7
         *
         *     前序遍历  3  9  20  15  7
         *     中序遍历  9  3  15  20  7
         *
         *     中序遍历的第一个就是根节点  确定根节点在前序遍历中的位置,构造左子树,以及右子树
         *
         */
        //为空的条件
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        return help(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

    }

    private TreeNode help(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        //构造根节点,
        TreeNode root = new TreeNode(preorder[iStart]);
        //找到根节点在中序遍历的位置 ,前序遍历的第一个节点就是根节点
        int index = 0;
        while (inorder[iStart + index] == preorder[pStart]) {
            index++;
        }
        //构建左子树
        root.left = help(preorder, pStart + 1, pStart + index, inorder, iStart, iStart + index - 1);
        //构建右子树
        root.right = help(preorder, pStart + index + 1, pEnd, inorder, iStart + index + 1, iEnd);
        return root;
    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     *
     * @param inorder
     * @param postorder
     * @return
     */
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        /**
         *  中序遍历  左  根 右
         *  后续遍历  左  右 根
         *
         */
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return buildTreeNode(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);

    }

    /**
     * 构建树
     *
     * @param postorder
     * @param inorder
     * @param postorderLeft
     * @param postorderRight
     * @param inorderLeft
     * @param inorderRight
     * @return
     */
    public TreeNode buildTreeNode(int[] postorder, int[] inorder, int postorderLeft, int postorderRight, int inorderLeft, int inorderRight) {
        if (postorderLeft > postorderRight || inorderLeft > inorderRight) {
            return null;
        }
        //构造根节点 //后续遍历的最后一个节点就是根节点
        TreeNode root = new TreeNode(postorder[postorderRight]);
        //找到根节点在中序遍历的位置,左右两边分别是左子树和右子树
        int inorderRoot = map.get(postorder[postorderRight]);
        //判断有几个节点
        int leftNodeNumber = inorderRoot - inorderLeft;
        root.left = buildTreeNode(postorder, inorder, postorderLeft, postorderLeft + leftNodeNumber - 1, inorderLeft, inorderRoot - 1);
        root.right = buildTreeNode(postorder, inorder, postorderLeft + leftNodeNumber, postorderRight - 1, inorderRoot + 1, inorderRight);
        return root;
        /**
         * 后序中右起第一位3肯定是根结点，我们可以据此找到中序中根结点的位置rootin；
         * 中序中根结点左边就是左子树结点，右边就是右子树结点，即[左子树结点，根结点，右子树结点]，我们就可以得出左子树结点个数为int left = rootin - leftin;；
         * 后序中结点分布应该是：[左子树结点，右子树结点，根结点]；
         * 根据前一步确定的左子树个数，可以确定后序中左子树结点和右子树结点的范围；
         * 如果我们要前序遍历生成二叉树的话，下一层递归应该是：
         * 左子树：root->left = pre_order(中序左子树范围，后序左子树范围，中序序列，后序序列);；
         * 右子树：root->right = pre_order(中序右子树范围，后序右子树范围，中序序列，后序序列);。
         * 每一层递归都要返回当前根结点root；
         */
    }
}
