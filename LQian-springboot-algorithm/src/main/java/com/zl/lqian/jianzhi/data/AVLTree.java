package com.zl.lqian.jianzhi.data;

/**
 * @Author zl
 * @Date 2020-04-15
 * @Des ${todo}
 */
public class AVLTree <TYPE extends Comparable<? super TYPE>> {


    private class Node{

        private TYPE element;

        private Node left;

        private Node right;

        private int height;

        public Node(TYPE element, Node left, Node right){
            this.element = element;
            this.left    = left;
            this.right   = right;
            this.height  = 0;
        }

    }

    /**
     * 数树的高度 = 节点到叶子节点的最远路径
     * @param t
     * @return
     */
    public int height(Node t){
        if (t == null){
            return -1;
        }
        return Math.max(height(t.left),height(t.right)) + 1;
    }


    public Node min(Node root){
        if(root.left== null){
            return root;
        }
        return min(root.left);
    }

    /**
     *                                    8                           16
     * RR左旋转                      0  1     16         2           8      18
     *                                   0 15   18      1        1   15      29
     * @param t                                   29    0
     * @return
     */
    public Node leftRotate(Node t){
        Node root = t.right;
        t.right = root.left;
        root.left = t;
        //计算hight
        root.height = Math.max
                (height(root.left), height(root.right)) + 1;
        //重新计算节点的高读
        t.height = Math.max(height(t.left),
                height(t.right)) + 1;
        return root;
    }

    /**                                                          30                            20
     * 左子树插入左子树破坏平衡需要 右旋转                         20        40     -->         10        30
     * @param t                                           10   25                       5       25    40
     * @return                                          5
     */
    public Node right(Node t){
        Node root = t.left;
        t.left = root.right;
        root.right = t;
        root.height = Math.max(height(root.left),height(root.right)) +1;
        t.height    = Math.max(height(t.left),height(t.right)) + 1;
        return root;
    }


    /**                                                          30                            20
     * 左子树插入右子树破坏平衡需要                               20        40     -->         10        30
     * @param t                                           10    25                      5         25    40
     * @return                                              14
     * 先左旋后右旋转
     */
    public Node leftRight(Node t){
        //先坐旋转后右旋转
        t.left= leftRotate(t.left);
        return  right(t);
    }

    /**
     * 右子树插入左节点 先右旋转后左旋
     * @param t
     * @return
     */
    public Node rightLeft(Node t){
        t.right = right(t.right);
        return leftRotate(t);
    }

    public Node insert(Node root,TYPE v){
        if (root == null){//新增节点
            return new Node(v,null,null);
        }
        //判断插入左子树还是右子树
        int compare = root.element.compareTo(v);
        if (compare > 0){//左子树
            //计算高度
            int height = height(root.left) - height(root.right);
            if (height == 2){
                //需要旋转
                //判断插入的是右子树还是左子树
                if (v.compareTo(root.left.element) < 0){//左子树的左子树,需要右旋转
                    root = right(root);
                }else {//左子树的右子树需要左右旋转
                    root = leftRight(root);
                }
            }
        }else if (compare < 0 ){//右子树
            int height = height(root.right) - height(root.left);
            if (height == 2){
                //需要旋转
                //判断插入的是右子树还是左子树
                if (v.compareTo(root.right.element) > 0){//右子树的右子树 需要坐旋转
                    root = leftRotate(root);
                }else {//右子树的左节点 需要右左旋转
                    root = rightLeft(root);
                }
            }
        }else {
            //不处理
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }
}
