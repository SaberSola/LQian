package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

/**
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 * <p>
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 654. 最大二叉树
 */
public class ConstructMaximumBinaryTree {

    /**
     * 找终止条件：当l>r时，说明数组中已经没元素了，自然当前返回的节点为null。
     * 每一级递归返回的信息是什么：返回的应该是当前已经构造好了最大二叉树的root节点。
     * 一次递归做了什么：找当前范围为[l,r]的数组中的最大值作为root节点，然后将数组划分成[l,bond-1]和[bond+1,r]两段，并分别构造成root的左右两棵子最大二叉树。
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return maxTree(nums, 0, nums.length - 1);
    }

    public TreeNode maxTree(int[] nums, int l, int r){
        if (l > r){
            return null;
        }
        //找到当前最大值的索引
        int bond = findMax(nums, l, r);
        //此时bond为根节点
        TreeNode root = new TreeNode(nums[bond]);
        root.left = maxTree(nums, l, bond - 1);
        root.right = maxTree(nums, bond + 1, r);
        return root;
    }

    //找最大值的索引
    public int findMax(int[] nums, int l, int r){
        int max = Integer.MIN_VALUE, maxIndex = l;
        for(int i = l; i <= r; i++){
            if(max < nums[i]){
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }


}
