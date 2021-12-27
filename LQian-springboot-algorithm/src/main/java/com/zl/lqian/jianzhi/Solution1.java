package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-04
 * @Des ${todo}
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 1  2  8   9
 * 2  4  9   12
 * 4  7  10  13
 * 6  8  11  15
 * 查找7返回true
 * 查找5 返回false
 * <p>
 * 解法一二次循环
 */
public class Solution1 {


    public static void main(String[] args) {

    }


    /**
     * 双层循环
     *
     * @param a
     * @param find
     * @return
     */
    private boolean find1(int[][] a, int find) {
        boolean inner = false;
        boolean outer = false;
        for (int i = 0; i < a.length; i++) {
            if (inner = false) {
                for (int j = 0; j < a[i].length; j++) {
                    if (a[i][j] == find) {
                        inner = true;
                        outer = true;
                        break;
                    }
                }
            }
            if (outer == true) {
                break;
            }
        }
        return outer;
    }


    /**
     * 1  2  8   9
     * 2  4  9   12
     * 4  7  10  13
     * 6  8  11  15
     * 从左下找
     * <p>
     * 每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序
     * 即对于左下角的值 m，m 是该行最小的数，是该列最大的数
     * <p>
     * <p>
     * 当 m < target，由于 m 已经是该行最大的元素，想要更大只有从列考虑，取值右移一位
     * 当 m > target，由于 m 已经是该列最小的元素，想要更小只有从行考虑，取值上移一位
     * 当 m = target，找到该值，返回 true
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        int rows = array.length;
        if (rows == 0) {
            return false;
        }
        int cols = array[0].length;
        if (cols == 0) {
            return false;
        }
        // 左下
        int row = rows - 1;
        int col = 0;
        while (row >= 0 && col < cols) {
            if (array[row][col] < target) {
                col++;
            } else if (array[row][col] > target) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 1  2  8   9
     * 2  4  9   12
     * 4  7  10  13
     * 6  8  11  15
     * 从左下找
     * <p>
     * 每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序
     * 即对于左下角的值 m，m 是该行最小的数，是该列最大的数
     * <p>
     * <p>
     * 当 m < target，由于 m 已经是该行最大的元素，想要更大只有从列考虑，取值右移一位
     * 当 m > target，由于 m 已经是该列最小的元素，想要更小只有从行考虑，取值上移一位
     * 当 m = target，找到该值，返回 true
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find2(int target, int[][] array) {
        int rows =  array.length;
        if (rows == 0) {
            return false;
        }
        int cols = array[0].length;
        if (cols == 0) {
            return false;
        }
        //从坐下开始查找
        int row = rows - 1;
        int col = 0;
        while (row >=0 && col < cols){
            if (target > array[row][col]){
                //列数+1
                col++;
            }else if (target <array[row][col]){
                //target 小于当前值 行数-1
                row --;
            }else {
                return true;
            }
        }


        return false;
    }
}
