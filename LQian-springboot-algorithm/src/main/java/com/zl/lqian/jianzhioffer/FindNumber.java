package com.zl.lqian.jianzhioffer;

/**
 * 第3题
 * 一个二维数组，每一行从左到右递增，每一列从上到下递增．
 * 输入一个二维数组和一个整数，判断数组中是否含有整数
 */
public class FindNumber {


    /**
     *  0  1  2  3  4  5    下标
     *
     *  1  2  3  4  5  6     0
     *  7  8  9  10 11 12    1
     *  13 14 15 16 17 18    2
     *  19 20 21 22 23 24    3
     *
     *  双指针
     * @param array
     * @param target
     * @return
     */
    public static boolean find(int[][] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }

        int row = 0;
        int column = array[0].length - 1;

        while (row < array.length && column >= 0) {
            if (array[row][column] == target){
                return true;
            } else if (array[row][column] > target){
                column --;
            }else {
                row ++;
            }
        }

        return false;
    }

    /**
     * 二分法的思路就是把
     * 整个二维数字拉伸为
     * 单个长数组
     * @param array
     * @param target
     * @return
     */
    public static boolean find2(int[][] array, int target){
        if (array == null || array.length == 0) {
            return false;
        }
        int left = 0;
        int right = array.length * array[0].length - 1;
        int col = array[0].length;

        while (left <= right){
            int mid = left + (right - left) / 2;
            int value = array[mid / col][mid % col];
            if (value == target){
                return true;
            }else if (value > target){
                right = mid -1;
            }else {
                left = mid + 1;
            }
        }
        return false;
    }
}
