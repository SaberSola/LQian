package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {


    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 疑惑即可
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        if (nums.length <= 1) {
            return ans;
        }
        for (int i = 0; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    /**
     * 260. 只出现一次的数字 III
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     *
     * @param nums
     * @return
     */
    public int[] singleNumber2(int[] nums) {

        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }


}
