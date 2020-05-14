package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-05-14
 * @Des ${todo}
 */
public class Solution40 {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * <p>
     * 说明：解集不能包含重复的子集。
     * <p>
     * 示例:
     * <p>
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 先确定子集的个数 1-n
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(0, nums, result, new ArrayList<Integer>());
        return null;

    }

    /**
     * 回溯 就是删除 从头开始
     * @param i
     * @param nums
     * @param res
     * @param tmp
     */
    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{1,2,3};
        Solution40 solution40 = new Solution40();
        solution40.subsets(nums);

    }


}

