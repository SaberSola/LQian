package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-10
 * @Des ${todo}
 */
public class Solution25 {


    public int jump(int[] nums) {

        int len = nums.length;
        if (len <= 1) {
            // 如果长度为小于1，直接到达
            return 0;
        }
        // 每一次起跳的起点
        int index = 0;
        // 每一次起跳的终点
        int max = nums[0];
        // 跳到终点的最小步数，至少有一步
        int step = 1;

        while (max < len - 1){ //调到终点结束
            // 循环获取 index->max 区间每个点可以到达的最远位置，其中最大的那个点就可以作为下一次的起点
            int curMax = 0;
            for (int i = index + 1; i <= max; i++){
                if (i + nums[i] > curMax) {
                    //当前节点可到达的最远的距离长
                    curMax = i + nums[i];
                    index = i;
                }
            }
            max = index + nums[index];
            step++;
        }

        return step;
    }
}
