package com.zl.lqian.bytedance;

public class FindDuplicate {

    /**
     * LeetCode287-寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * 首先明确前提，整数的数组 nums 中的数字范围是 [1,n]。考虑一下两种情况：
     * <p>
     * 如果数组中没有重复的数，以数组 [1,3,4,2]为例，我们将数组下标 n 和数 nums[n] 建立一个映射关系 f(n)f(n)，
     * 其映射关系 n->f(n)为：
     * 0->1
     * 1->3
     * 2->4
     * 3->2
     * 我们从下标为 0 出发，根据 f(n)f(n) 计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推，直到下标超界。这样可以产生一个类似链表一样的序列。
     * 0->1->3->2->4->null
     * <p>
     * 如果数组中有重复的数，以数组 [1,3,4,2,2] 为例,我们将数组下标 n 和数 nums[n] 建立一个映射关系 f(n)f(n)，
     * 其映射关系 n->f(n) 为：
     * 0->1
     * 1->3
     * 2->4
     * 3->2
     * 4->2
     * 同样的，我们从下标为 0 出发，根据 f(n)f(n) 计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推产生一个类似链表一样的序列。
     * 0->1->3->2->4->2->4->2->……
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        /**
         * 以下标和数字组成环
         */
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while (pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }
}
