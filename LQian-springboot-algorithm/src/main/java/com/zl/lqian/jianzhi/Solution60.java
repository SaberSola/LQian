package com.zl.lqian.jianzhi;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author zl
 * @Date 2020-04-13
 * @Des ${todo}
 */
public class Solution60 {

    private int cnt = 0;
    private PriorityQueue<Integer> low = new PriorityQueue<>();

    private PriorityQueue<Integer> high = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    /**
     * 插入排序？
     * @param num
     */
    public void Insert(Integer num) {

        cnt++;
        // 如果为奇数的话
        if ((cnt & 1) == 1) {
            // 由于奇数，需要存放在大顶堆上
            // 但是呢，现在你不知道num与小顶堆的情况
            // 小顶堆存放的是后半段大的数
            // 如果当前值比小顶堆上的那个数更大
            if (!low.isEmpty() && num > low.peek()) {
                // 存进去
                low.offer(num);
                // 然后在将那个最小的吐出来
                num = low.poll();
            } // 最小的就放到大顶堆，因为它存放前半段
            high.offer(num);
        } else {
            // 偶数的话，此时需要存放的是小的数
            // 注意无论是大顶堆还是小顶堆，吐出数的前提是得有数
            if (!high.isEmpty() && num < high.peek()) {
                high.offer(num);
                num = high.poll();
            } // 大数被吐出，小顶堆插入
            low.offer(num);
        }
    }

    public Double GetMedian() {
        double res = 0;
        // 奇数
        if ((cnt & 1) == 1) {
            res = high.peek();
        } else {
            res = (high.peek() + low.peek()) / 2.0;
        }
        return res;
    }
}

