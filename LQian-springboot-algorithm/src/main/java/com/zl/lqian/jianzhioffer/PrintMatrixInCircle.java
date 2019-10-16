package com.zl.lqian.jianzhioffer;

import java.util.ArrayList;

/**
 * @Author zl
 * @Date 2019-10-09
 * @Des 顺时针打印矩阵
 */
public class PrintMatrixInCircle {


    /**
     * 顺时针打印矩阵
     * 输入二维数组
     * 1   2   3   4
     * <p>
     * 5   6   7   8
     * <p>
     * 9   10  11  12
     * <p>
     * 13  14  15  16
     * <p>
     * 输出: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
     *
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix(int[][] matrix) {

        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null) {
            return null;
        }
        int start = 0;
        while (matrix[0].length > start * 2 && matrix.length > start * 2) {
            saveOneCircle(matrix,start,list);
            start++;
        }
        return null;
    }
    /**
     * 顺时针打印矩阵
     * 输入二维数组
     *    1   2   3   4
     *
     *    5   6   7   8
     *
     *    9   10  11  12
     *
     *   13  14  15  16
     *
     * 输出: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
     * @param matrix
     * @param start
     * @param list
     */
    private static void saveOneCircle(int[][] matrix, int start, ArrayList<Integer> list) {
        int endX = matrix[0].length - 1 - start; // 列
        int endY = matrix.length - 1 - start;  // 行
        // 从左往右
        for (int i = start; i <= endX; i++)
            list.add(matrix[start][i]);
        // 从上往下
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++)
                list.add(matrix[i][endX]);
        }
        // 从右往左（判断是否会重复打印）
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--)
                list.add(matrix[endY][i]);
        }
        // 从下往上（判断是否会重复打印）
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; i--)
                list.add(matrix[i][start]);
        }
    }
}
