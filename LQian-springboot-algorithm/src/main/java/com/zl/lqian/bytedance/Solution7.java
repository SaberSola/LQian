package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 */
public class Solution7 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, sum, new ArrayList<>(), result);
        return result;
    }

    public void dfs(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result) {
        if (root == null){
            return;
        }
        List<Integer> subList = new ArrayList<>(list);
        subList.add(root.val);
        if (root.left == null && root.right == null) {
            //叶子节点
            if (sum == root.val){
                result.add(subList);
            }
            //不可以往下走了
            return;
        }
        //如果没有到叶子节点,继续往下搜索
        dfs(root.left,sum - root.val,subList,result);
        dfs(root.right,sum - root.val,subList,result);
    }
}
