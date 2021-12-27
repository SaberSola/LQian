package com.zl.lqian.bytedance;

/**
 * 41.缺失的第一个正数
 */
public class FirstMissingPositive {

    /**
     * 第一个缺失的正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * <p>
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,0]
     * 输出：3
     * 示例 2：
     *
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 示例 3：
     *
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/first-missing-positive
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        /**
         *
         * nums = [1,2,0]
         * 输出：3
         *
         * nums = [3,4,-1,1]
         * 输出：2
         *
         *  思路是调整数组顺序
         *  比如[1,2,0]可以调整为 [1，2，3]
         *  去缺失的是3
         *  [3,4,-1,1] -> [1, -1, 3, 4]
         *  缺失的是2
         */
        int len = nums.length;
        for (int i = 0; i < len ; i++){
            while (nums[i] >= 1 && nums[i] < len && nums[nums[i] - 1] != nums[i]){
                //需要交换位置
                swap(nums,i,nums[i] -1);
            }
        }
        for (int i = 0; i < len ; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return len + 1;
    }

    public void swap(int nums[],int index1,int index2){
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}
