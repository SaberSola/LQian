package com.zl.lqian.bytedance;

import java.util.Arrays;

public class TriangleNumber {


    public static int triangleNumber(int[] nums) {
        /**
         *
         * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
         *
         * 示例 1:
         *
         * 输入: [2,2,3,4]
         * 输出: 3
         * 解释:
         * 有效的组合是:
         * 2,3,4 (使用第一个 2)
         * 2,3,4 (使用第二个 2)
         * 2,2,3
         * 三角形的个数 双边只和大于第三遍
         * 先排序,
         *
         */
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = n - 1; i > 1; i--) {
            int compare = nums[i];
            int j = i - 1;
            int k = 0;
            while (j > k) {
                if (nums[k] + nums[j] > compare) {
                    ans += j - k;
                    j--;
                } else {
                    k++;
                }
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] num = new int[]{2, 2, 3, 4};
        System.out.println(triangleNumber(num));
    }
}
