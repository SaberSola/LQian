package com.zl.lqian.daypractice.find;

public class RBTree <TYPE extends Comparable<? super TYPE>> {

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    private Node root;

    public boolean isEmpty() { return root == null; }

    public int size() { return size(root); }

    private int size(Node n) { return n == null ? 0 : n.N; }

    public int height() { return height(root); }

    private int height(Node n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * 红黑树的旋转                                    8
     *                                          6        9
     *                                       5    7
     * 假设此时6节点是红节点 需要旋转 此时
     * 左旋转
     * @param t
     * @return
     */
    private Node leftRotate(Node t){





        return null;
    }


    private class Node{

        private TYPE type;

        private Node left;

        private Node right;

        /** 这个子树中节点的总数***/
        private int N;

        private boolean color;


        public Node(TYPE type,int N,Node left,Node right,boolean color){
            this.type = type;
            this.left = left;
            this.right = right;
            this.color = color;
            this.N = N;
        }

    }

}
