package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 */
public class Subsets {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 获取到数组的字节
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {

        backtrack(0, nums, res, new ArrayList<Integer>());

        return res;
    }

    private void backtrack(int start, int nums[], List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));//已运行就添加
        if (start == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            //继续递归
            backtrack(i + 1, nums, res, path);
            path.remove(path.size() - 1);
        }
    }
}
