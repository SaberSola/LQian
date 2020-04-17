package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-07
 * @Des ${todo}
 */
public class Solution23 {


    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
     * @param sequence
     * @return
     */

    public boolean helpVerify(int [] sequence, int start, int root){
        if(start >= root)return true;
        int key = sequence[root];
        int i;
        //找到左右子数的分界点
        for(i=start; i < root; i++)
            if(sequence[i] > key)
                break;
        //在右子树中判断是否含有小于root的值，如果有返回false
        for(int j = i; j < root; j++)
            if(sequence[j] < key)
                return false;
        return helpVerify(sequence, start, i-1) && helpVerify(sequence, i, root-1);
    }
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0)return false;
        return  helpVerify(sequence, 0, sequence.length-1);
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     *  如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
     *
     *                            10
     *                         5       15
     *                       3   6  13    16
     *
     *
     * 后续遍历  3 5 6   13 15 16  10
     *
     * 根节点是最后一个 找到左右子树的分割点 只要左子树都小于根节点 右节点都大于根节点就是 搜索二叉树
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST2(int [] sequence) {
        if(sequence == null || sequence.length == 0)return false;
        return  helpVerify1(sequence, 0, sequence.length-1);
    }
    public boolean helpVerify1(int [] sequence, int start, int root){
        if(start >= root)return true;
        int key = sequence[root];//根节点值,左右子树的分界点
        int i;
        for (i = start; i < root; i ++){
            if (sequence[i] > key){
                break;
            }

        }
        //此时的i就是属右子树的第一个节点
        //判断右子树有没有小于根节点的
        for (int j = i; j < root; j ++){
            if (sequence[j] < key){
                return false;
            }
        }

        return helpVerify1(sequence, start, i -1) && helpVerify1(sequence, i, root-1);
    }


}
