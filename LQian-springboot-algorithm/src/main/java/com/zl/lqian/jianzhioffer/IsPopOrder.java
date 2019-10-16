package com.zl.lqian.jianzhioffer;

import java.util.Stack;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des 输入俩个整数序列 第一个序列表示压入顺序，判断第二个序列是否为弹出顺序
 */
public class IsPopOrder {

    /**
     * 输入  push 1 2 3 4 5
     *      pop  5 4 3 2 1
     *
     * @param pushList
     * @param popList
     * @return
     */
    public static boolean isPopOrder(int[] pushList, int[] popList) {


        if (pushList == null || popList == null) {
            return false;
        }

        int point = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < pushList.length; i++) {
            stack.push(pushList[i]);
            while (!stack.isEmpty() && stack.peek() == popList[point]) {
                stack.pop();
                point++;
            }

        }
        return stack.isEmpty();

    }
}
