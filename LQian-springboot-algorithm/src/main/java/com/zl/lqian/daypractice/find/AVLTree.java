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

    /**   height             height
     *    3          10                   右右旋转           10      仍然失衡 左左旋转            5
     *    2       5      15   0         --------->       5     15  --------------->        3    10
     *    1     2                                      3                                 2         15
     *    0       3                                  2
     * 左右旋转(左子树的右节点)
     * 插入节点3 整个树失衡
     * 此时5是失衡点
     *
     * @param t
     * @return
     */
    public Node leftRightRotate(Node t){
        t.left = rightRotate(t.left);
        return leftRotate(t);
    }


    /**
     * 右子树的左节点 (右左旋转)
     *            10                           10                                       15
     *         5      15     左左旋转         5     15          仍然失衡                 10   18
     *                   20  -------->               18       ----------------->     5       20
     *                18                               20
     *  15为失横点
     * @param t
     * @return
     */
    public Node rightLeft(Node t){
        t.right = leftRotate(t.right);
        return rightRotate(t);
    }

    public Node insert(TYPE x){
        return insert(x,root);
    }

    /**
     * @param x
     * @param t
     * @return
     */
    private Node insert(TYPE x,Node t){
        if (t == null){//新增节点
            return new Node(x,null,null);
        }
        int cmp = x.compareTo(t.element);
        if (cmp < 0 ){ //左子树插入
            t.left = insert(x,t.left);
            //判断树是否失衡
            int height = height(t.left) - height(t.right);
            if (height == 2){
                int leftcmp = x.compareTo(t.left.element);
                if (leftcmp < 0){ //根节点 左子树的左节点
                    //开始左左旋转
                    t = leftRotate(t);
                }else {
                    //属于左右旋转 根节点左子树的右节点
                    t = leftRightRotate(t);
                }
            }
        }else if (cmp > 0){//右子树插入
            t.right = insert(x,t.right);
            int height = height(t.right) - height(t.left);
            if (height == 2){ //此处节点失衡
                int rightcmp = x.compareTo(t.right.element);
                if (rightcmp > 0){
                    //右子树的右节点
                    //右右旋转
                   t = rightRotate(t);
                }else {
                    //右左旋转
                   t = rightLeft(t);
                }
            }
        }else {
            //todo 节点相同 做一些事情
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    public Node delete(TYPE x){
        return delete(x,root);
    }


    private Node delete(TYPE x,Node t){
        if (t == null){
            return null;
        }
        //开始查找节点
        int cmp = x.compareTo(t.element);
        if (cmp < 0){ // 属于根节点的左子树
            t.left = delete(x,t.left);
            //计算height
            int leftheight = height(t.left) - height(t.right);
            if (leftheight == 2){ //此处节点失衡
                int leftcmp = x.compareTo(t.left.element);
                if (leftcmp < 0){ //左左旋转
                    t = leftRotate(t);
                }else {
                    //左右
                    t = leftRightRotate(t);
                }
            }
        }else if (cmp > 0){ //属于节点右子树
            int rightheight = height(root.right) - height(root.left);
            if (rightheight == 2){//次数节点失衡
                int rightcmp = x.compareTo(t.right.element);
                if (rightcmp > 0){
                    //右右旋转
                    t = rightRotate(t);
                }else {
                    //youzuo
                    t = rightLeft(t);
                }
            }
        }else { //找到节点
            //找到要删除的节点
            if (t.left == null) {return t.right;}
            if (t.right == null) {return t.left;}
            Node node = t;
            t = min(t.right);
            t.right = deleteMin(t.right);// min(x.right)
            //todo 删除操作
            node.left = t.left;
            t.height = 1 + Math.max(height(t.left), height(t.right));
        }
        return t;
    }


   private Node min(Node root){
        if (root.left == null){
            return root;
        }
        return min(root.left);
   }

   private Node deleteMin(Node root){
        if (root.left == null){
            return root.right;
        }
       root.left = deleteMin(root.left);
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

    public static void main(String[] args) {
    }
}
