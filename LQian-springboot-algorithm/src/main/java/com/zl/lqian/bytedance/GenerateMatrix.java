package com.zl.lqian.bytedance;


/**
 * 59. 螺旋矩阵 II
 */
public class GenerateMatrix {

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * 螺旋矩阵,分层填充,先填充waiceng,然后填充内层
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int num = 1;
        int[][] res = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        while (left <= right && top <= bottom){
            //填充第一行
            for (int column = left; column <= right; column++){
                res[top][column] = num;
                num++;
            }
            //填充最后一列
            for (int row = top +1; row <= bottom; row++){
                res[row][right] = num;
                num++;
            }
            //处理最后一行和第一列
            if (left < right && top < bottom){
                for (int column = right - 1; column > left; column--) {
                    res[bottom][column] = num;
                    num++;
                }
                for (int row = bottom; row > top; row--) {
                    res[row][left] = num;
                    num++;
                }
            }
            //开始缩圈
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }

}
