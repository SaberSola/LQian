package com.zl.lqian.bytedance;

/**
 * 面试题 10.03. 搜索旋转数组
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 * <p>
 * 示例1:
 * <p>
 * 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 * 输出: 8（元素5在该数组中的索引）
 * 示例2:
 * <p>
 * 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 * 输出：-1 （没有找到）
 */
public class Search {


    /**
     * 二分查找,判断mid实在递增区间还是在递减区间
     *
     * @param arr
     * @param target
     * @return
     */
    public int search(int[] arr, int target) {
        int n = arr.length;
        int result = -1;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r + 1) >> 1;
            if (arr[l] == target) return l;
            else if (arr[l] == arr[mid]) l++;
            else if (arr[l] < arr[mid]) {
                if (arr[l] > target || arr[mid] < target) l = mid;
                else {
                    l = l + 1;
                    r = mid;
                }
            } else {
                if (arr[l] > target && arr[mid] < target) l = mid;
                else {
                    l = l + 1;
                    r = mid;
                }
            }
        }
        return result;
    }

}
