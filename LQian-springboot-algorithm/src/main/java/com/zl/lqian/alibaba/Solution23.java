package com.zl.lqian.alibaba;

import java.util.ArrayList;
import java.util.List;

public class Solution23 {


    /**
     * 给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 减枝
     * @param nums
     * @return
     */
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(0, nums, result, new ArrayList<Integer>());
        return result;
    }

    /**
     * dfs 深度优先遍历
     * @param i
     * @param nums
     * @param res
     * @param tmp
     */
    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length;j ++){
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
