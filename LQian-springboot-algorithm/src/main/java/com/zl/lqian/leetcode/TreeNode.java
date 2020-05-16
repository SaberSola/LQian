package com.zl.lqian.leetcode;

import lombok.Data;

/**
 * @Author zl
 * @Date 2020-05-15
 * @Des ${todo}
 */
@Data
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
