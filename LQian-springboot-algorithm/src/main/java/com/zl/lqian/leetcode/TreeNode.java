package com.zl.lqian.leetcode;

import lombok.Data;

/**
 * @Author zl
 * @Date 2020-05-15
 * @Des ${todo}
 */
@Data
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }
}
