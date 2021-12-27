package com.zl.lqian.alibaba;

import sun.print.DialogOnTop;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

    /**
     * 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> sunMap = new HashMap<>();
        for (int i = 0, len = nums.length - 1; i < len; i++){
            if (sunMap.containsKey(target - nums[i])){
                return new int[]{sunMap.get(target - nums[i]), i};
            }
            sunMap.put(nums[i], i);
        }
        return new int[0];
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> sunMap = new HashMap<>();
        for (int i = 0 ; i <nums.length ; i++){
            if (sunMap.containsKey(target-nums[i])){
                return new int[]{sunMap.get(target - nums[i]), i};
            }

            sunMap.put(nums[i], i);
        }
        return new int[0];
    }
}
