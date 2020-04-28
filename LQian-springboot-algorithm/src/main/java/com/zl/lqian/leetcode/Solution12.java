package com.zl.lqian.leetcode;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2020-04-28
 * @Des ${todo}
 */
public class Solution12 {

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * <p>
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 双指针发
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        int len = nums.length - 1;
        for (int i = 0; i < nums.length - 2; i++) {
            int L = i + 1;
            int R = len;
            while (L != R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum > target) {
                    R--;
                    // 解决nums[right]重复
                    while (L != R && nums[R] == nums[R + 1])
                        R--;
                } else {
                    L++;
                    // 解决nums[left]重复
                    while (L != R && nums[L] == nums[L - 1])
                        L++;
                }
            }
            //解决num[i]重复
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                i++;
            }

        }
        return result;
    }
}
