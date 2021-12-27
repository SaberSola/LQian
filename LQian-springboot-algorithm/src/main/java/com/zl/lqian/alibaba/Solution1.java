package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

    public List<Integer> inorderTraversal(TreeNode  v) {
        List<Integer> resultList = new ArrayList<>();
        print(v,resultList);
        return resultList;
    }

    /**
     * 中序遍历  左子树 -- root -- 右子树
     * @param node
     * @param results
     */
    private void print(TreeNode node,List<Integer> results){
        if (node == null){
            return;
        }
        print(node.getLeft(),results);
        results.add(node.getVal());
        print(node.getRight(),results);
    }

}
