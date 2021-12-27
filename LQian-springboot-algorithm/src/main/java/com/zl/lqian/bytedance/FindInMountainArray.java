package com.zl.lqian.bytedance;

/**
 * 1095. 山脉数组中查找目标值
 */
public class FindInMountainArray {


    /**
     * 山峰数字是 先递增后递减
     * 输入：array = [1,2,3,4,5,3,1], target = 3
     * 输出：2
     * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
     *
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, int[] mountainArr) {

        /**
         * 1:先使用二分法找到数组的峰值。
         *
         * 2:在峰值左边使用二分法寻找目标值。
         *
         * 3:如果峰值左边没有目标值，那么使用二分法在峰值右边寻找目标值。
         */
        int l = 0, r = mountainArr.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (mountainArr[mid] < mountainArr[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        //peak属于山峰值
        int peak = l;
        //先从左边查找
        int index = binarySearch(mountainArr, target, 0, peak, true);
        if (index != -1) {
            return index;
        }
        //在查找右边
        return binarySearch(mountainArr, target, peak + 1, mountainArr.length - 1, false);
    }

    public int binarySearch(int[] mountainArr, int target, int l, int r, boolean flag) {
        if (!flag) {
            target *= -1;
        }
        while (l <= r) {
            int mid = (l + r) / 2;
            int cur = mountainArr[mid] * (flag ? 1 : -1);
            if (cur == target) {
                return mid;
            } else if (cur < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
