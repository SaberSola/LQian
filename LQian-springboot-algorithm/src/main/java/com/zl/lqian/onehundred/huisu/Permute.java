package com.zl.lqian.onehundred.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permute {

    List<List<Integer>> ans = new ArrayList<>();

    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * 输入: [1,2,3]
     * <p>
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> prmute(int[] nums) {

        return ans;
    }

    private void dfs(int[] nums, List<Integer> tmp) {
        System.out.println(Arrays.toString(nums) + "," + tmp);
        if (tmp.size() == nums.length){
            ans.add(new ArrayList<>(tmp));
        }else {
            for (int num : nums) {
                if (!tmp.contains(num)) {
                    tmp.add(num);
                    dfs(nums, tmp);
                    tmp.remove(tmp.size() - 1);
                }
            }

        }

    }


}
