package com.zl.lqian.alibaba;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution47 {

    /**
     * candidates = [2, 3, 6, 7], target = 7 为例：
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     *
     * @param candidates 数组
     * @param begin      起始
     * @param len        数组长度
     * @param target     目标
     * @param path       根节点到叶子节点的路径
     * @param res        结果集类表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0){
            return;
        }
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //从begin开始搜索 深度遍历
        for (int i= begin; i < len;  i++){
            path.addLast(candidates[i]);
            dfs(candidates, i, len, target - candidates[i], path, res);
            //状态重置
            path.removeLast();
        }
    }
}
