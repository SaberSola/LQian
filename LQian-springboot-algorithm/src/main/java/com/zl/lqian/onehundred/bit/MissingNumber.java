package com.zl.lqian.onehundred.bit;

public class MissingNumber {


    /**
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     * 输入: [3,0,1]
     * 输出: 2
     * 输入: [9,6,4,2,3,5,7,0,1]
     * 输出: 8
     *
     * @param nums
     * @return
     */
    static int missingNumber(int[] nums) {
        int length = nums.length;
        int result = (length + 1) * length / 2;
        for (int e : nums) {
            result -= e;
        }
        return result;
    }


    /**
     * 位运算操作
     *
     * @param nums
     * @return
     */
    static int missingNumber2(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i] ^ i;
        }
        return result ^ nums.length;
    }



    public static void main(String[] args) {
        int[] num = new int[]{0, 1, 2, 3, 5};
        System.out.println(missingNumber(num));
    }
}
