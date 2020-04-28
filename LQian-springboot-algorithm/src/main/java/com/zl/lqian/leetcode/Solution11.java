package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-04-28
 * @Des ${todo}
 */
public class Solution11 {


    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 双指针法
     * 每次固定一个数，然后通过双指针遍历取另外两个数，使得三数之和为 0 。
     * *** 需要注意的就是每个去重的点，要使得符合要求的三元组中每个数值只使用一次。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //首先排序
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            //最小数的都 > 0 说明三数字之和也大于0
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                //遇到相同的数字就跳过
                continue;
            }
            int L = i + 1, R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));

                    // 和为 0 的三个数，相同的值只遍历一次。
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    // 和为0的三个数，相同的值只遍历一次。
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    //取完一组 双指针同时移动
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return ans;
    }
}
