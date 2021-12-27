package com.zl.lqian.bytedance;

public class SearchMatrix {

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        //从左下角卡开始搜索
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] < target) {
                //比目标小,向上搜索
                col++;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }
}
