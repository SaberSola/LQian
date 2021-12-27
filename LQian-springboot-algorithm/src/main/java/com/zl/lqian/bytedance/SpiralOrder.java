package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        //核心是确定好总共打印几圈
        //行数
        int m = matrix.length;
        if (m == 0) {
            return ans;
        }
        //列数
        int n = matrix[0].length;
        //第一列
        int c1 = 0;
        //最后一列
        int c2 = n - 1;
        //第一行
        int r1 = 0;
        //最后一行
        int r2 = m - 1;
        //计算圈数
        int times = Math.min(m, n) % 2 == 0 ? Math.min(m, n) / 2 : Math.min(m, n) / 2 + 1;
        for (int i = 0; i < times; i++) {
            //开始答应第一行
            for (int c = c1; c <= c2; c++) {
                ans.add(matrix[r1][c]);
            }
            //答应最后一列
            for (int r = r1 + 1; r <= r2; r++) {
                ans.add(matrix[r][c2]);
            }
            //打印最后一行
            if (r1 < r2 && c1 < c2) {
                //最后一行
                for (int c = c2 - 1; c > c1; c--) {
                    ans.add(matrix[r2][c]);
                }
                //第一列
                for (int r = r2; r > r1; r--) {
                    ans.add(matrix[r][c1]);
                }
            }

            //移动内圈
            c1++;
            c2--;
            r1++;
            r2--;
        }
        return ans;
    }


    /**
     * 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        /**
         * 输入：nums = [4,5,6,7,0,1,2], target = 0
         * 输出：4
         */
        //思路就是二分查找
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //判断mid实在递增的,还是递减的
            if (nums[mid] >= nums[lo]) {
                //这里说明mid处于递增区间
                //在判断target和mid的关系
                if (nums[mid] > target && target >= nums[lo]) {
                    //说明target处于递增的区间
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                //处于后半段
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }
}
