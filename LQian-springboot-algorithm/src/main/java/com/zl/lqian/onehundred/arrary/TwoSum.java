package com.zl.lqian.onehundred.arrary;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> sumMap = new HashMap<>();
        int temp[]= new int[2];
        for (int i = 0 ; i < nums.length; i ++){
            if(sumMap.containsKey(target - nums[i])){
                temp[0] = sumMap.get(target - nums[i]);
                temp[1] = i;
            }
            sumMap.put(nums[i],i);
        }

        return temp;

    }
}
