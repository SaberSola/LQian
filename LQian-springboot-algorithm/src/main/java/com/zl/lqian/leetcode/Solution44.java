package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-05-15
 * @Des ${todo}
 */
public class Solution44 {




    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        help(root,result);
        return result;
    }

    private void help(TreeNode root,List<Integer> result){
        if (root == null){
            return;
        }
        help(root.left, result);
        result.add(root.val);
        help(root.right, result);

    }

}
