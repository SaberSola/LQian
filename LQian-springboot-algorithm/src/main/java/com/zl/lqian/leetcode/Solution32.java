package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-05-12
 * @Des ${todo}
 */
public class Solution32 {


    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     *
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int row = matrix.length;//行数,列数
        if (row == 0) {
            return list;
        }
        int col = matrix[0].length;

        //获取四个角
        int r1 = 0;
        int r2 = row -1;
        int c1 = 0;
        int c2 = col-1;
        int times = Math.min(row, col) % 2 == 0 ? Math.min(row, col) / 2 : Math.min(row, col) / 2 + 1;
        //开始打印
        for (int i = 0; i < times; i ++){
            //打印第一行
            for (int c = c1; c <= c2 ; c++){
                list.add(matrix[r1][c]);
            }
            //打印最后一列
            for (int r = r1 + 1; r1 <= r2; r++){
                list.add(matrix[r][c2]);
            }
            //开始打印最后一行和第一列
            if (r1 < r1 && c1 < c2){
                //打印最后一行
                for (int c = c2-1; c > c1; c--){
                    list.add(matrix[r2][c]);
                }
                //打印第一列
                for (int r = r2 ; r > r1; r--){
                    list.add(matrix[r][c1]);
                }
            }
            //开始缩圈
            r1++;
            r2--;
            c1++;
            c2--;

        }

        return list;

    }


}
