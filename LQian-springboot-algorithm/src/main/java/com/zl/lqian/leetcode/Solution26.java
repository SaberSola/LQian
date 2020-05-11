package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-05-10
 * @Des ${todo}
 */
public class Solution26 {


    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 1) {
            return res;
        }
        Boolean[] used = new Boolean[len];
        for (int i = 0; i < len; i++) {
            used[i] = false;
        }
        //一次搜索的路径
        List<Integer> path = new ArrayList<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }


    private void dfs(int[] num, int len, int depth, List<Integer> path, Boolean[] used, List<List<Integer>> res) {
        //退出递归
        if (depth == len) {
            res.add(path);
            return;
        }
        //开始一次遍历
        for (int i = 0; i < len; i++) {
            if(used[i]){
                continue;
            }
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(num[i]);
            used[i] = true;
            dfs(num, len, depth + 1, newPath, used, res);
            used[i] = false;

        }

    }

}
