package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.ArrayList;

/**
 * @Author zl
 * @Date 2020-04-13
 * @Des ${todo}
 */
public class Solution59 {


    /**
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，
     * 按结点数值大小顺序第三小结点的值为4。
     * 中序遍历 有序列表
     * @param pRoot
     * @param k
     * @return
     */
    Common.TreeNode KthNode(Common.TreeNode pRoot, int k) {
        ArrayList<Common.TreeNode> result = new ArrayList<>();
        if(pRoot == null || k<=0){
            return null;
        }
        foreach(result,pRoot);
        if(result.size()>=k){
            return result.get(k-1);
        }else{
            return null;
        }
    }

    private void foreach(ArrayList<Common.TreeNode> result, Common.TreeNode node) {

        if(node.left != null){
            foreach(result, node.left);
        }
        result.add(node);
        if(node.right != null){
            foreach(result, node.right);
        }
    }
}
