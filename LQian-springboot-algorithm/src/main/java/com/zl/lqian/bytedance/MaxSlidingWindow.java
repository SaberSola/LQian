package com.zl.lqian.bytedance;

import java.util.LinkedList;

public class MaxSlidingWindow {

    /**
     * 滑动窗口最大值
     * 双端队列,
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        /**
         * 双端队列,头结点存放最大值,队列的长度为K
         */
        LinkedList<Integer> queue = new LinkedList();

        // 结果数组
        int[] result = new int[nums.length - k + 1];
        //遍历
        for (int i = 0; i < nums.length; i++) {
            //判断队列的值,要是队列的值小于当前的值,则把队列的值移出去,
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效 如果当前的值,已经下雨滑动窗口的左侧,需要从队列中移出去
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            //时间窗口是否形成
            if (i + 1 >= k) {
                result[i - k + 1] = nums[queue.peek()];
            }
        }
        return result;
    }
}
