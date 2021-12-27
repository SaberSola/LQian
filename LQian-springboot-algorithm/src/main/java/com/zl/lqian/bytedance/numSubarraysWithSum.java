package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;

/**
 * 930. 和相同的二元子数组
 */
public class numSubarraysWithSum {


    /**
     * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * <p>
     * 子数组 是数组的一段连续部分。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
     * 示例 2：
     * <p>
     * 输入：nums = [0,0,0,0,0], goal = 0
     * 输出：15
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param goal
     * @return
     */
    public static int numSubarraysWithSum(int[] nums, int goal) {
        //假设原数组的前缀和数组为 sum，且子数组 (i,j](i,j] 的区间和为 goal，那么sum[j]−sum[i]=goal。因此我们可以枚举 j ，每次查询满足该等式的 i 的数量。
        //使用hash表,用hash表记录每一尊重前缀出现的次数, 我们只需要查询 sum - goal的次数
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }
}
