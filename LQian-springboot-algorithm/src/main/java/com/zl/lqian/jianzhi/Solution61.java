package com.zl.lqian.jianzhi;

import java.util.ArrayList;

/**
 * @Author zl
 * @Date 2020-04-14
 * @Des ${todo}
 */
public class Solution61 {


    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
     * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
     * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     *
     * 快慢指针？
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        if (size < 1 || num.length < size){
            return new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        int right = size-1;
        int left = 0;
        while (right < num.length){
            list.add(max(left,right,num));
            right++;
            left++;
        }
        return list;
    }

    public int max(int left,int right,int[] num){
        int max = num[left];
        for (int i = left;i <= right; i ++){
            if (max < num[i]){
                max = num[i];
            }
        }
        return max;
    }
}
