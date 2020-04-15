package com.zl.lqian.jianzhi.data;



/**
 * @Author zl
 * @Date 2020-04-15
 * @Des ${todo}
 *
 * 红黑树的特征
 */
public class RedBlackTree<TYPE extends Comparable<? super TYPE>>  {













    private class Node {

        private TYPE type;

        private RedBlackTree.Node left;

        private RedBlackTree.Node right;

        private RedBlackTree.Node parent;

        /**
         * 这个子树中节点的总数
         ***/

        private boolean red;


        public Node(TYPE type, RedBlackTree.Node left, RedBlackTree.Node right, boolean red) {
            this.type = type;
            this.left = left;
            this.right = right;
            this.red = red;
        }

    }

}
