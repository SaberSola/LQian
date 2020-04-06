package com.zl.lqian.jianzhi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-04-06
 * @Des ${todo}
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 * ，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Solution19 {


    /**
     * c1         c2
     * <p>
     * r1   1  2   3   4
     * 5  6   7   8
     * 9  10  11  12
     * r2  13 14  16  17
     * <p>
     * 1  2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 打印
     * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * <p>
     * 首先确定打印几圈,是由行确定的
     * 打印俩圈
     *
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int m = matrix.length;//行数
        if (m == 0) {
            return ans;
        }
        int n = matrix[0].length;//列数
        //四个角的上下限制
        int c1 = 0;
        int c2 = n - 1;
        int r1 = 0;
        int r2 = m - 1;
        //求打印的圈数
        int times = Math.min(m, n) % 2 == 0 ? Math.min(m, n) / 2 : Math.min(m, n) / 2 + 1;
        //循环打印

        /**
         *      c1    c2-1   c2
         * <p>
         * r1   1  2   3   4           matrix[r1][c]  横着第一行
         *      5  6   7   8           matrix[r][c2]  竖着最后一行
         *      9  10  11  12  r2 -1   matrix[r2][c]  横着最后一行
         * r2   13 14  16  17          matrix[r][c1]  竖着第一行
         *
         *
         * <p>
         * 1  2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
         * 打印
         * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
         */
        for (int i = 0; i < times; i++) {
            //开始打印
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1; r <= r2; r++) ans.add(matrix[r][c2]);
            //开始移动内全
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }

            c1++;
            c2--;
            r1++;
            r2--;
        }
        return ans;
    }
}
