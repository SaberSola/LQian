package com.zl.lqian.bytedance;

import javafx.application.Application;
import javafx.stage.Stage;

public class Rotate {

    /**
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        /**
         *
         * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
         *
         * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像
         *
         *
         /**
         *
         * [
         *   [ 5, 1, 9,11],
         *   [ 2, 4, 8,10],
         *   [13, 3, 6, 7],
         *   [15,14,12,16]
         * ],
         *
         * [
         *   [15,13, 2, 5],
         *   [14, 3, 4, 1],
         *   [12, 6, 8, 9],
         *   [16, 7,10,11]
         * ]
         * 内圈外圈 然后交换位置
         *
         */
        if(matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }
        int nums = matrix.length;
        int times = 0;
        while (times <= nums<<1){
            //内圈的长度就是 length - tims * 2
            int length = nums - (times << 1);
            for (int i = 0; i < length - 1; i++){
                int temp = matrix[times][times + i];
                //开始叫交换位置

                /**
                 *
                 * [
                 *   [ 5, 1, 9,11],
                 *   [ 2, 4, 8,10],
                 *   [13, 3, 6, 7],
                 *   [15,14,12,16]
                 * ],
                 *
                 * [
                 *   [15,13, 2, 5],
                 *   [14, 3, 4, 1],
                 *   [12, 6, 8, 9],
                 *   [16, 7,10,11]
                 * ]
                 *
                 *
                 *
                 */
                matrix[times][times + i] = matrix[times + length - i - 1][times];
                matrix[times + length - i - 1][times] = matrix[times + length -1][times + length - i -1];
                matrix[times + length -1][times + length - i -1] = matrix[times + i][times + length - 1];
                matrix[times + i][times + length - 1] = temp;
            }
            times++;

        }



    }

}
