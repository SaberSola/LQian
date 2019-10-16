package com.zl.lqian.jianzhioffer;

import java.util.Arrays;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des 输入一个数组判断是不是二叉树后序遍历
 */
public class VerifySequenceOfBST {


    public static boolean VerifySquenceOfBST(int[] sequence) {

        if (sequence == null || sequence.length == 0) {
            return false;
        }

        int rstart = 0;
        int rootIndex = sequence.length - 1; //根节点下标
        for (int i = 0; i < rootIndex; i++) {
            if (sequence[i] < sequence[rootIndex])
                rstart++;
        }

        if (rstart == 0) {
            VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, rootIndex));
            return true;
        }

        for (int i = rstart; i < rootIndex; i++) {
            if (sequence[i] <= sequence[rootIndex]) {
                return false;
            }
        }
        VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, rstart));
        VerifySquenceOfBST(Arrays.copyOfRange(sequence, rstart, rootIndex));
        return true;
    }

}
