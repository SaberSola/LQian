package com.zl.lqian.alibaba;

public class Solution27 {

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
      * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        //任何数字0异或都是自己本身,自己异或自己是0
        int answer = 0;
        for (int i =0; i < nums.length; i++){
            answer^= nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(1^2);
    }
}
