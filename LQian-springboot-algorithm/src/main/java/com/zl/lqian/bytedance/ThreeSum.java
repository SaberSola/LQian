package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        //结果集
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        //先排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            //去重复,定义两个指针
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去重 移动指针
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    //去重
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    } // 去重

                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }

            }
        }
        return ans;
    }
}
