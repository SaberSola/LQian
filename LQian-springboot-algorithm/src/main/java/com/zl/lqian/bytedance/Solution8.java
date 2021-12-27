package com.zl.lqian.bytedance;

/**
 * @author zhanglei 33. 搜索旋转排序数组
 */
public class Solution8 {

    /**
     * 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        /**
         * [3,4,5,1,2]
         */
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //前半部分是升序排序
            if (nums[0] <= nums[mid]) {
                //前半部分
                if (nums[0] < target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                //属于后半部分升序排序
                if (nums[mid] < target && target < nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

        }
        return -1;
    }
}
