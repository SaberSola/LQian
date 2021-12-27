package com.zl.lqian.bytedance;

/**
 * 498. 对角线遍历
 */
public class FindDiagonalOrder {


    /**
     * 对角线遍历,
     * 首先确定遍历次数  行数 + 列数 - 1
     * 遍历次数为偶数向上遍历
     * 奇数为向下遍历
     *
     * @param matrix
     * @return
     */
    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        int[] res = new int[rowLength * columnLength];
        int times = rowLength + columnLength - 1;
        int m = 0; //纵坐标
        int n = 0; //横坐标
        int resIndex = 0;

        for (int i = 0; i < times; i++) {
            if (i % 2 == 0) {
                //偶数向上遍历
                while (m >= 0 & n < columnLength) {
                    res[resIndex] = matrix[m][n];
                    resIndex++;
                    m--;
                    n++;
                }
                if (n < columnLength) {
                    m++;
                } else {
                    m = m + 2;
                    n--;
                }

            }else {
                while (m < rowLength && n >= 0) {
                    res[resIndex] = matrix[m][n];
                    resIndex++;
                    m++;
                    n--;
                }
                if (m < rowLength) {
                    n++;
                }else{
                    m--;
                    n=n+2;
                }
            }
        }
        return res;
    }


}
