package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des 将二叉搜索转为双向链表
 */
public class BinaryToLinked {

    /**
     * 二叉搜索树
     */
    static class BinaryTreeNode {
        int data;
        BinaryTreeNode leftNode;
        BinaryTreeNode rightNode;
    }

    public static BinaryTreeNode convert(BinaryTreeNode root) {

        //指向双向链表的尾节点
        BinaryTreeNode lastNode = convertNode(root, null);
        //找到转换后的链表的头节点
        BinaryTreeNode pHead = lastNode;
        while (pHead != null && pHead.leftNode != null) {
            pHead = pHead.leftNode;
        }
        //返回链表头节点
        return pHead;
    }

    /**
     * 处理二叉树转换为双链表的节点
     * @param root
     * @param lastNode
     * @return
     */
    public static BinaryTreeNode convertNode(BinaryTreeNode root, BinaryTreeNode lastNode) {

        if (root == null) {
            return null;
        }
        BinaryTreeNode current = root;

        //递归处理左子树
        if (current.leftNode != null) {
            lastNode = convertNode(current.leftNode, lastNode);
        }
        //将当前节点的左指针指向已经转换好的链表的最后一个位置
        current.leftNode = lastNode;
        //将已经转换好的链表的最后一个节点的右指针指向当前节点
        if (lastNode != null) {
            lastNode.rightNode = current;
        }
        //更新已经转换好的链表的最后一个节点
        lastNode = current;
        //递归处理右子树
        if (current.rightNode != null) {
            lastNode = convertNode(current.rightNode, lastNode);
        }
        return lastNode;
    }

}
