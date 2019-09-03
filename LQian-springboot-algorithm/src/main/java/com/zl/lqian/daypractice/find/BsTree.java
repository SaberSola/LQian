package com.zl.lqian.daypractice.find;

/**
 * @Author zl
 * @Date 2019-09-03
 * @Des 二叉树 大部分使用递归的思想
 */
public class BsTree<KEY extends Comparable, VALUE> {

    private Node root;            //根节点


    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    public VALUE get(KEY key){
        return get(root,key);
    }

    private VALUE get(Node root, KEY key) {
        if(root == null)
            return null;
        int cmp = root.key.compareTo(key);
        if (cmp == 0){
            return root.value;
        }else if (cmp > 0){  //root节点 > 插入节点 此时应该查找做节点
            return get(root.left, key);
        }else {              //root 节点 < 插入节点

        }
        return root.value;
    }

    public void put(KEY key, VALUE val) {
        // 查找 key，找到则更新它的值，否则为它创建一个新的结点
        put(root, key, val);
    }

    /**
     * 构建二叉树
     * 左节点 < 根节点 < 右节点
     *
     * @param root
     * @param key
     * @param value
     * @return
     */
    private Node put(Node root, KEY key, VALUE value) {
        if (root == null) {                     //节点为null 新增一个节点
            return new Node(key, value, 1);
        }
        int cmp = root.key.compareTo(key);
        if (cmp == 0) {
            root.value = value;
        }else if (cmp > 0){                     //插入的节点key < root节点此为右
            root.left = put(root.left, key, value);
        }else {                                 //插入节点 > root 节点
            root.right = put(root.right,key,value);
        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    private class Node {
        private KEY key;           //key

        private VALUE value;       //VALUE

        private Node left;         //左子树

        private Node right;        //you子

        private int N;             //该节点为根节点的子树节点数目

        public Node(KEY key, VALUE value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
}
