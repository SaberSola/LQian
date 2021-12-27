package com.zl.lqian.bytedance;

public class RotateArrary {


    /**
     * 189. 旋转数组 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 示例 1:
     *
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        /**
         * 输入: nums = [1,2,3,4,5,6,7], k = 3
         * 输出: [5,6,7,1,2,3,4]
         * 解释:
         * 向右旋转 1 步: [7,1,2,3,4,5,6]
         * 向右旋转 2 步: [6,7,1,2,3,4,5]
         * 向右旋转 3 步: [5,6,7,1,2,3,4]
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/rotate-array
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         * 先旋转整个数组,在分别旋转 0，k-1 k-n的数组
         * 1,2,3,4,5,6,7
         *
         * 7 6 5 4 3 2 1
         *
         * 5 6 7 1 2 3 4
         *
         */
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


}
