package com.zl.lqian.jianzhi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des 小明很喜欢数学, 有一天他在做数学作业时, 要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 */
public class Solution42 {


    /**
     * 双指针法
     * <p>
     * <p>
     * 思路：
     * 输入sum=20（1，2，3，4，5，6，7，8，9，10，11，12，13，14，15
     * 1，定义两个指针，左指针从1开始，右指针从2开始
     * 循环开始
     * 2，求和（1+2 = 3
     * 3，如果判断3小于20，右指针++，2变为3，求和3+3=6。循环一直到右指针=6，和为21。
     * 4，else if 判断21大于20，左指针++，1变为2，和减去左指针值，和为21-1=20。
     * 5，else 和与输入一样，存数。   【再把右指针++，求和，求剩余组合】
     * 循环结束
     * <p>
     * 类似于滑动窗口
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> resp = new ArrayList<>();
        if (sum <= 0) {
            return resp;
        }
        int leftP = 1;
        int rightP = 2;
        int sumVal = leftP + rightP;
        while (sumVal > rightP) {
            if (sumVal < sum) {
                rightP++;
                sumVal = sumVal + rightP;
            } else if (sumVal > sum) {
                sumVal = sumVal - leftP;
                leftP++;
            } else if (sumVal == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = leftP; i <= rightP; i++) {
                    list.add(i);
                }
                resp.add(list);

                rightP++;
                sumVal += rightP;
            }
        }

        return resp;

    }

    /**
     * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer>> resp = new ArrayList<>();
        if (sum <= 0) {
            return resp;
        }
        int left = 1;
        int right = 2;
        int sumVal = left + right;
        while (sumVal > right) {
            if (sumVal < sum) {
                right++;
                sumVal = sumVal + right;
            } else if (sumVal > sum) {
                sumVal = sumVal - left;
                left++;
            } else if (sumVal == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left; i <= right; i++) {
                    list.add(i);
                }
                resp.add(list);
                right++;
                sumVal = sumVal + right;
            }

        }

        return resp;
    }
}
