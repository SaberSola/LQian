package com.zl.lqian.bytedance;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 739. 每日温度
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class DailyTemperatures {


    /**
     * 请根据每日 气温 列表 temperatures，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0 来代替。
     * <p>
     * 示例 1:
     * <p>
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出:[1,1,4,2,1,1,0,0]
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        //采用单调栈,栈ding的元素和栈内的元素比较,小于直接进栈,大于则作比较
        //注意栈内存的是天数
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!deque.isEmpty() && temperatures[deque.peek()] < temperatures[i]) {
                int idx = deque.pop();
                res[idx] = i - idx;
            }
            deque.push(i);
        }
        return res;
    }
}
