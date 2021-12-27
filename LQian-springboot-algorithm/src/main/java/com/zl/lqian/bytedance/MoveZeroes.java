package com.zl.lqian.bytedance;

public class MoveZeroes {

    /**283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        /**
         *  双指针,left 指向已经处理结束的部分
         *        right 指向未处理的头部
         *        当nums[right] != 0 的时候, 交换left和right的位置
         *
         *     [0,1,0,3,12]  left = 0 right = 0
         *     [1,0,0,3,13]  left = 1 right = 1
         *      1,0,0,3,13   left = 1 right =2
         *      1,3,0,0,13   left = 2 right =3
         */

        int left = 0;
        int right = 0;
        int len = nums.length;
        while (right < len) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
