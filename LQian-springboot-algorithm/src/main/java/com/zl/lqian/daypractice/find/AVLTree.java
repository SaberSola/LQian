package com.zl.lqian.daypractice.find;

/**
 * @Author zl
 * @Date 2019-09-05
 * @Des 二叉平衡树
 */
public class AVLTree <TYPE extends Comparable<? super TYPE>> {

    private Node root;


    /**
     * 树的高度: 对于任意的节点N,N的高度 = N到叶子节点的最远路径长度
     * @param t
     * @return
     */
    public int height(Node t){
        if (t == null){
            return -1;
        }
        return Math.max(height(t.left),height(t.right)) + 1;
    }

    /**
     *                        4                         3
     *                    3       5                  2    4
     *                  2                           1       5
     *                 1
     * 此时 4节点的 左节点 3 失衡
     * 3节点开始旋转  Node newRoot  =4.left
     * 老节点（4）height- 1 4的左节点 必须大于 新节点3 小于4本身 所以这时候 4.left = 3.right
     * 3.right = 4; 3的新right节点 必须 大于3
     *
     * 1:t 为老根的节点 左节点高度 - 右节点高度 > 1
     * 2:t.left 变为新的根节点  Node newRoot = t.left
     * 3:
     *
     * @param t
     * @return
     */
    public Node leftRotate(Node t){

        Node root = t.left; //这是新的根节点
        t.left = root.right;
        root.right = t;
        root.height = Math.max
                (height(root.left), height(root.right)) + 1;
        //重新计算节点的高读
        t.height = Math.max(height(t.left),
                height(t.right)) + 1;
        return root;
    }


    /**
     *                    2            3
     *                  1  3         2  4
     *                      4       1    5
     *                       5
     * 此2节点的 3子树失衡点
     * 右选转
     * Node newRoot = 2.left（3节点） 变成根节点
     * 2.right =  newroot.left
     * newroot.left = 2 节点
     *
     * @param t
     * @return
     */
    public Node rightRotate(Node t){
        Node root = t.right;
        t.right = root.left;
        root.left = t;
        root.height = Math.max
                (height(root.left), height(root.right)) + 1;
        //重新计算节点的高读
        t.height = Math.max(height(t.left),
                height(t.right)) + 1;
        return root;
    }


    private class Node{

        private TYPE element;

        private Node left;

        private Node right;

        private int height;

        public Node(TYPE element,Node left,Node right){
            this.element = element;
            this.left    = left;
            this.right   = right;
            this.height  = 0;
        }

    }
}
