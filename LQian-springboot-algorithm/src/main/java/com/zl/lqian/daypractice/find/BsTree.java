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

    public VALUE get(KEY key) {
        return get(root, key);
    }

    private VALUE get(Node root, KEY key) {
        if (root == null)
            return null;
        int cmp = root.key.compareTo(key);
        if (cmp == 0) {
            return root.value;
        } else if (cmp > 0) {  //root节点 > 插入节点 此时应该查找做节点
            return get(root.left, key);
        } else {              //root 节点 < 插入节点
            return get(root.right, key);
        }
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
        } else if (cmp > 0) {                     //插入的节点key < root节点此为右
            root.left = put(root.left, key, value);
        } else {                                 //插入节点 > root 节点
            root.right = put(root.right, key, value);
        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public VALUE select(int k) {
        return select(root, k).value;
    }

    private Node select(Node x, int k) {
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k)
            return select(x.left, k);
        else if (t < k)
            return select(x.right, k - t - 1);
        else
            return x;

    }


    /**
     * 找到最小的节点 其实就是找到最小的左节点
     *
     * @return
     */
    public Node min() {
        return min(root.left);
    }


    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x);
    }

    public Node max() {
        return max(root.right);
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    /**
     * 删除最小的节点
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(KEY key) {
        root = delete(root, key);
    }

    private Node delete(Node x, KEY key) {
        //
        if (x == null) return null;
        int cmp = key.compareTo(x.key);  //判断是左节点 根节点 还是右节点
        if (cmp > 0){  //节点 > 根节点 开始往
            x.right = delete(x.right,key);
        }else if (cmp < 0){
            x.left = delete(x.left,key);
        }else {//找到节点
            if (x.left == null){       //删除的节点的左节点为null 返回删除节点的有节点为代替之前删除的节点
                return x.right;
            }
            if (x.right == null){      //删除的节点的左节点为null 返回删除节点的有节点为代替之前删除的节点
                return x.left;
            }
            //左右节点都存在的情况
            Node t = x;         //设置临时节点 t = x;
            //将x设置为后继的最小的节点  先取有字数，不断检查左子树 找到为空的那个节点 此时这个这个节点正好是右节点最小的 但是大于所有的 x 左节点
            x = min(x.right);
            x.right = deleteMin(x.right);// min(x.right)
            x.left = t.left; //这是x.left 变为 原x节点 也就是t节点的 左节点
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
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
