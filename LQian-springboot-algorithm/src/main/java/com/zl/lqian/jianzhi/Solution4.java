package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-04
 * @Des ${todo}
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，
 * 请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,5,7,8,3,6 和中序遍历序列{4,2,7,5,8,1,3,6}，则重建二叉树并返回
 *
 * 前序是根左右
 * 中序是左根右
 */
public class Solution4 {



    public static void main(String[] args) {
        int [] a = {1,2,4,5,3,6,7};
        int [] b = {4,2,5,1,6,3,7};
        reConstructBinaryTree(a,0,a.length-1,b,0,b.length-1);
    }


    /**
     *
     *
     *
     *
     *                            1
     *                         2      3
     *                       4   5  6   7
     *
     *
     *   前序: 根左右
     * {1,2,4,5,3,6,7};
     *   中序: 左根右
     *   {4,2,5,1,6,3,7};
     *
     *
     * @param pre
     * @param startPre
     * @param endPre
     * @param in
     * @param startIn
     * @param endIn
     * @return
     */
    private static Common.TreeNode reConstructBinaryTree(int [] pre, int startPre, int endPre, int [] in, int startIn, int endIn) {

        System.out.println(startPre + " " +startIn+ " " +endIn);
        if(startPre>endPre||startIn>endIn)
            return null;
        Common.TreeNode root=new Common.TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){ // i= 3
                /**
                 *    {1,2,4,5,3,6,7};   1是根节点 4，2，5为左子树
                 *
                 *    {4,2,5,1,6,3,7};   1是根据点 4，2，5为左子树
                 *
                 *    构建左子树
                 *    i=3 startPre = 0 endPre= 6 startIn =  0，endIn = 6
                 *        startPre = 1 endPre = 3 StartIn = 0，endIn = 2;
                 *    构建右子树 越界就是为节点
                 *
                 */
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
                break;
            }

        return root;
    }
    /**
     *

     *
     * i-starin是在计算当前节点左子树节点个数；
     * i-starin+starpre是确定当前节点左子树在pre里的位置；
     * 根据中序遍历+前序遍历的特点，pre[starpre+1]就是当前节点的左子节点，
     * pre[i-starin+starpre+1]就是当前节点的右子节点；巧妙的是那个判定条件，越界即左右子节点不存在
     *
     *
     *
     *
     *
     *
     */


    public Common.TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        //数组长度为0的时候要处理
        if(pre.length == 0){
            return null;
        }

        int rootVal = pre[0];

        //数组长度仅为1的时候就要处理
        if(pre.length == 1){
            return new Common.TreeNode(rootVal);
        }

        //我们先找到root所在的位置，确定好前序和中序中左子树和右子树序列的范围
        Common.TreeNode root = new Common.TreeNode(rootVal);
        int rootIndex = 0;
        for(int i=0;i<in.length;i++){
            if(rootVal == in[i]){
                rootIndex = i;
                break;
            }
        }

        //递归，假设root的左右子树都已经构建完毕，那么只要将左右子树安到root左右即可
        //这里注意Arrays.copyOfRange(int[],start,end)是[)的区间
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,rootIndex+1),Arrays.copyOfRange(in,0,rootIndex));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre,rootIndex+1,pre.length),Arrays.copyOfRange(in,rootIndex+1,in.length));

        return root;
    }

    /**
     *
     * 因为是树的结构，一般都是用递归来实现。
     *
     * 用数学归纳法的思想就是，假设最后一步，就是root的左右子树都已经重建好了，那么我只要考虑将root的左右子树安上去即可。
     *
     * 根据前序遍历的性质，第一个元素必然就是root，那么下面的工作就是如何确定root的左右子树的范围。
     *
     * 根据中序遍历的性质，root元素前面都是root的左子树，后面都是root的右子树。那么我们只要找到中序遍历中root的位置，就可以确定好左右子树的范围。
     *
     * 正如上面所说，只需要将确定的左右子树安到root上即可。递归要注意出口，假设最后只有一个元素了，那么就要返回。
     */



}
