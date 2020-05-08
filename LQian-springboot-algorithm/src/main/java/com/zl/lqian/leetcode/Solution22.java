package com.zl.lqian.leetcode;

import java.util.*;

/**
 * @Author zl
 * @Date 2020-05-07
 * @Des ${todo}
 */
public class Solution22 {


    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     * 思路：根据示例 1：输入: candidates = [2,3,6,7]，target = 7。
     *
     * 候选数组里有 2 ，如果找到了 7 - 2 = 5 的所有组合，再在之前加上 2 ，就是 7 的所有组合；
     * 同理考虑 3，如果找到了 7 - 3 = 4 的所有组合，再在之前加上 3 ，就是 7 的所有组合，依次这样找下去；
     * 上面的思路就可以画成下面的树形图。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;

        // 排序是为了提前终止搜索
        Arrays.sort(candidates);
        dfs(candidates,len,target,0,new ArrayDeque<>(),res);
        return res;
    }

    /**
     *
     * @param candidates 数组输入
     * @param len        数组的长度
     * @param residue    剩余数值
     * @param begin      本轮搜索的起点下标
     * @param path
     * @param res
     */
    private void dfs(int[] candidates,
                     int len,
                     int residue,
                     int begin,
                     Deque<Integer> path,
                     List<List<Integer>> res) {
        if (residue == 0){ //退出递归条件
            res.add(new ArrayList<>(path));//加入结果
            return;
        }

        for (int i = begin; i < len; i++){
            //排序的前提下, < 0 直接break
            if (residue - candidates[i] < 0){
                break;
            }
            path.addLast(candidates[i]);
            //开始递归
            dfs(candidates, len, residue - candidates[i], i, path, res);
            path.removeLast();
        }
    }

    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        process(0,candidates,target,list);

        return lists;
    }

    private void process(int index, int[] candidates,int target, List<Integer> list){
        if (target < 0){
            return;
        }
        if (target ==0 ){
            lists.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = index; i < candidates.length;i++){
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            process(i + 1, candidates, target - candidates[i], list);
            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        Integer a  = Integer.MAX_VALUE;
        Integer b  =a +1;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a < b);
    }

}
