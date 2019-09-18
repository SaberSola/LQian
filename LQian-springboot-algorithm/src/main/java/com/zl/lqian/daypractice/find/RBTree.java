package com.zl.lqian.daypractice.find;

import java.lang.reflect.Type;

/**
 * 红黑树
 *
 * @param <TYPE>
 */
public class RBTree<TYPE extends Comparable<? super TYPE>> {

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    private Node root;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return n == null ? 0 : n.N;
    }

    public int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * 红黑树的旋转                                    8                  6
     * red   6        9  ----->   5    8 red
     * 5    7                     7   9
     * 假设此时6节点是红节点 需要旋转 此时
     * 左旋转
     *
     * @param t
     * @return
     */
    private Node leftRotate(Node t) {
        Node root = t.left;
        t.left = root.right;
        root.right = t;
        /**
         * 和二叉树相比 需要奉还颜色
         */
        t.color = RED;
        root.N = t.N;
        t.N = 1 + size(t.left) + size(t.right);
        return root;
    }


    /**
     * t
     * 红黑树的右旋转                                     8    root                           10
     * 7        10   red        ------> red  8    11
     * 9    11                      7   9
     *
     * @return
     */
    private Node rightRotate(Node t) {
        Node root = t.right; //新的根节点
        t.right = root.left;
        root.left = t;
        /**
         * 更换颜色
         */
        t.color = RED;
        root.N = t.N;
        t.N = 1 + size(t.left) + size(t.right);
        return root;
    }

    /**
     * (red)
     * 颜色翻转                              root                                 root
     * left（red）    rigt(red)  ------->        left      right
     */
    private void flipColors(Node t) {
        t.color = RED;
        t.left.color = BLACK;
        t.right.color = BLACK;
    }

    private boolean isRed(Node t) {
        return t.color == RED;
    }

    private void put(TYPE type) {
        root = put(root, type);
        root.color = BLACK;
    }

    /**
     * @param t
     * @param type
     * @return
     */
    private Node put(Node t, TYPE type) {
        if (t == null) {
            return new Node(type, 1, null, null, RED);
        }
        int cmp = type.compareTo(t.type);
        if (cmp > 0) { //右子树
            t.right = put(t.right, type);
        } else if (cmp < 0) { // 左子树
            t.left = put(t.left, type);
        } else { //
            t.type = type;
        }
        //翻转树 以及颜色
        if (isRed(t.right) && !isRed(t.left)) {   //右子树为红节点 左子树为黑节点 需要旋转 右旋转
            t = rightRotate(t);
        } else if (isRed(t.left) && isRed(t.left.left)) { //连续俩条左节点是红色节点 坐旋转
            t = leftRotate(t);
        }else if (isRed(t.left) && isRed(t.right)){       //左右节点 全是红色 需要翻转节点颜色
            flipColors(t);
        }else {
            //todo 正常情况不处理
        }
        //计算size
        t.N = 1 + size(t.right) + size(t.left);
        return t;
    }

    private void delete(TYPE type){
        root = delete(root,type);
        root.color = BLACK;
    }

    private Node delete(Node t,TYPE type){

        return null;
    }


    private class Node {

        private TYPE type;

        private Node left;

        private Node right;

        /**
         * 这个子树中节点的总数
         ***/
        private int N;

        private boolean color;


        public Node(TYPE type, int N, Node left, Node right, boolean color) {
            this.type = type;
            this.left = left;
            this.right = right;
            this.color = color;
            this.N = N;
        }

    }

}
