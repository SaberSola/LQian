package com.zl.lqian.onehundred.slicewindow;

import java.util.ArrayList;
import java.util.List;

public class FindContinuousSequence {


    /**
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {

        List<int[]> res = new ArrayList<>();
        int i = 1;
        int j = 1;
        int win = 0;
        while (i <= target / 2){
            if (win < target){
                win += j;
                j++;
            }else if (win > target){
                win -= i;
                i++;
            }else {
                int array [] = new int[j-1];
                for (int k = i; k < j; k++) {
                    array[k-i] = k;
                }
                res.add(array);
                win-= i;
                i++;

            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
