package com.zl.lqian.bytedance;

/**
 * 153. 寻找旋转排序数组中的最小值
 */
public class FindMin {

    /**
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * <p>
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * <p>
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
     * 示例 2：
     * <p>
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
     * 示例 3：
     * <p>
     * 输入：nums = [11,13,15,17]
     * 输出：11
     * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        /**
         * nums = [3,4,5,1,2]
         */
        while (low < high) {
            int pivot = low + (high - low) / 2;
            // 如果中间值小于最大值，则最大值减小
//            疑问：为什么 high = mid;而不是 high = mid-1;
//            解答：{4,5,1,2,3}，如果high=mid-1，则丢失了最小值1
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                //              如果中间值大于最大值，则最小值变大
//                疑问：为什么 low = mid+1;而不是 low = mid;
//                解答：{4,5,6,1,2,3}，nums[mid]=6，low=mid+1,刚好nums[low]=1
//                继续疑问：上边的解释太牵强了，难道没有可能low=mid+1,正好错过了最小值
//                继续解答：不会错过!!! 如果nums[mid]是最小值的话，则其一定小于nums[high],走if，就不会走else了
                low = pivot + 1;
            }
        }
        //        疑问：为什么while的条件是low<high,而不是low<=high呢
//        解答：low<high，假如最后循环到{*,10,1,*}的这种情况时，nums[low]=10,nums[high]=1,nums[mid]=10,low=mid+1,
//             直接可以跳出循环了,所以low<high,此时low指向的就是最小值的下标;
//             如果low<=high的话，low=high，还会再不必要的循环一次，此时最后一次循环的时候会发生low==high==mid，
//             则nums[mid]==nums[high]，则会走一次else语句，则low=mid+1,此时low指向的是最小值的下一个下标，
//             则需要return[low-1]
        return nums[low];
    }
}
