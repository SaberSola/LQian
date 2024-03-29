package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-29
 * @Des
 */
public class Solution18 {


    /**
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *  
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int l = 0;
        int r = 1;
        while (r < nums.length){
            if (nums[l] != nums[r]){
                nums[l + 1] = nums[r];
                l++;
            }
            r++;
        }
        return l +1;
    }

    public static int removeElement(int[] nums, int val) {
        int ans = nums.length;
        for (int i =0;i < ans;){
            if (nums[i] == val){
                nums[i] = nums[ans-1];
                ans--;
            }else {
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int a[] = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(a));
    }


}
