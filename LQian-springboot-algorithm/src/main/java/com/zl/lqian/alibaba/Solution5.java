package com.zl.lqian.alibaba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution5 {

    /**
     * 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //排序之后的 num[i] > 0 则 之后的肯定都 > 0 跳出循环
            if (nums[i] > 0) {
                break;
            }
            //去小重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //定义左右两个指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    } // 去重
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                }else {
                    right --;
                }
            }
        }
        return ans;
    }
}
