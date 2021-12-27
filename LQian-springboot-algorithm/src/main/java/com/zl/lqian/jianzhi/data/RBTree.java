package com.zl.lqian.jianzhi.data;


import lombok.Data;

/**
 * @Author zl
 * @Date 2020-04-15
 * @Des ${todo}
 * <p>
 * 红黑树的特征
 */
public class RBTree<T extends Comparable<T>> {

    private final RBTreeNode<T> root;
    private java.util.concurrent.atomic.AtomicLong size =
            new java.util.concurrent.atomic.AtomicLong(0);
    private volatile boolean overrideMode = true;

    public RBTree() {
        this.root = new RBTreeNode<T>();
    }

    public RBTree(boolean overrideMode) {
        this();
        this.overrideMode = overrideMode;
    }

    public boolean isOverrideMode() {
        return overrideMode;
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }

    /**
     * number of tree number
     *
     * @return
     */
    public long getSize() {
        return size.get();
    }

    /**
     * get the root node
     *
     * @return
     */
    private RBTreeNode<T> getRoot() {
        return root.getLeft();
    }

    public T find(T value) {
        RBTreeNode<T> dataRoot = getRoot();
        while (dataRoot != null) {
            int cmp = dataRoot.getValue().compareTo(value);
            if (cmp > 0) {
                dataRoot = dataRoot.right;
            } else if (cmp < 0) {
                dataRoot = dataRoot.left;
            } else {
                return dataRoot.value;
            }

        }
        return null;
    }

    /**
     * 插入
     *
     * @param node
     * @return
     */
    private T addNode(RBTreeNode<T> node) {


        return null;
    }

    /**
     * 左旋转
     *
     * @param node
     */
    private void rotateLeft(RBTreeNode<T> node) {


    }

    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node != null) {
            node.setParent(parent);
            if (parent == root) {
                node.setParent(null);
            }
        }
    }


    private class RBTreeNode<T extends Comparable<T>> {
        private T value;
        private RBTreeNode left;
        private RBTreeNode right;
        private RBTreeNode parent;
        private boolean red;

        public RBTreeNode() {
        }

        public RBTreeNode(T value) {
            this.value = value;
        }

        public RBTreeNode(T value, boolean isRed) {
            this.value = value;
            this.red = isRed;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public RBTreeNode getLeft() {
            return left;
        }

        public void setLeft(RBTreeNode left) {
            this.left = left;
        }

        public RBTreeNode getRight() {
            return right;
        }

        public void setRight(RBTreeNode right) {
            this.right = right;
        }

        public RBTreeNode getParent() {
            return parent;
        }

        public void setParent(RBTreeNode parent) {
            this.parent = parent;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }
    }

}
