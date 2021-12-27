package com.zl.lqian.bytedance;

import java.util.Random;

/**
 * @author zhanglei
 */
public class Solution3 {

    Random random = new Random();
    /**
     * 数组中第K个最大元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     *
     * @param a     数组
     * @param l     左边界
     * @param r     右边界
     * @param index
     * @return
     */
    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);

        if (q == index) {
            // 如果刚好索引q就是想要的索引，则直接返回
            return a[q];

        } else {
            // 如果不是，比较q 与 index ,确定下次要检索的区间, 要么是[q+1, right], 要么就是[left, q-1]
            //index 之前都是排好序的,
            //q < index
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        // 1. 随机数范围: [0, r-l+1) 同时加l, 则是 [l, r+1) = [l, r] 也就是在这个[l,r] 中随机选一个索引出来
        int i = random.nextInt(r - l + 1) + l;
        // 2. a[i]， a[r], 也就是将随机数先放在[l,r]a[r]上
        swap(a,i,r);
        //开始排序
        return partition(a, l, r);
    }

    /**
     *  快排思路想不明白？本质是构建这样一种序列：[小 小 小 i 大 大 大 j 待比较 待比较 待比较 基准]。
     *  i左边的都是小于的，i和j中间都是大于的。每一个待比较的数，判断如果大于基准，放到i和j之间；如果小于基准，交换到i的左边。
     * @param a
     * @param l
     * @param r
     * @return
     */
    public int partition(int[] a, int l, int r) {
        //获取随机数
        int x = a[r];
        //小堆最后一个元素
        int i = l - 1;
        for (int j = l; j < r; ++j) {
            //小于基准值的,都放到i的左边
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        //上边for把所有小于基准值的x的都设置在了i的左边
        //5. 既然已经将<x的值都放在一边了，现在将x也就是nums[r] 跟nums[i+1]交换，从而分成两个区间[l.i+1]左, [i+2, r]右，左边区间的值都小于x
        //交换基准位置
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int nums[] = new int[]{1,2,3,9,7,5,4};
        solution3.findKthLargest(nums,4);
        int a = 1;
        System.out.println(++a);
        System.out.println(a);
    }
}
