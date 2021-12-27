package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CombinationSum {

    /**
     * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
     * <p>
     * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
     * <p>
     * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        /**
         * 输入: candidates = [2,3,6,7], target = 7
         * 输出: [[7],[2,2,3]]
         */
        /**
         *               2              3           6                7
         *           2  3  6  7    2  3  6  7    2  3  6  7      2  3  6  7
         *           2  3  6  7    2  3  6  7    2  3  6  7      2  3  6  7
         *           2  3  6  7    2  3  6  7    2  3  6  7      2  3  6  7
         *          ....
         *          ....
         */
        //还是深度搜索
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(candidates, target, res, list, 0);
        return res;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            combine.add(candidates[i]);
            dfs(candidates, target - candidates[i], ans, combine, i);
            combine.remove(combine.size() - 1);
        }

    }
}

