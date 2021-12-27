package com.zl.lqian.bytedance;

import java.util.*;

public class Permute {

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * <p>
     * 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        /**
         *               1           2            3
         *             2  3        1   3        1    2
         *          3       2    3       1    2         1
         *  总共有以上几种情况
         */
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        boolean used[] = new boolean[n];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, 0, path, res, used);
        return res;
    }

    private void dfs(int[] nums, int depth, Deque<Integer> path, List<List<Integer>> res, boolean[] used) {
        //开始深度优先搜索
        if (depth == nums.length) {
            //终止递归的条件
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //判断当前数字有么有使用过
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            //开始继续深度搜索第二层
            dfs(nums, depth + 1, path, res, used);
            //移除上一个使用的
            path.removeLast();
            used[i] = false;
        }
    }
}
