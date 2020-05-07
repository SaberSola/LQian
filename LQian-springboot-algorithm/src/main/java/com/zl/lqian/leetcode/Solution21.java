package com.zl.lqian.leetcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author zl
 * @Date 2020-05-05
 * @Des ${todo}
 */
public class Solution21 {


    /**
     *
     判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

     数字 1-9 在每一行只能出现一次。
     数字 1-9 在每一列只能出现一次。
     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


     上图是一个部分填充的有效的数独。

     数独部分空格内已填入了数字，空白格用 '.' 表示。

     示例 1:

     输入:
     [
     ["5","3",".",".","7",".",".",".","."],
     ["6",".",".","1","9","5",".",".","."],
     [".","9","8",".",".",".",".","6","."],
     ["8",".",".",".","6",".",".",".","3"],
     ["4",".",".","8",".","3",".",".","1"],
     ["7",".",".",".","2",".",".",".","6"],
     [".","6",".",".",".",".","2","8","."],
     [".",".",".","4","1","9",".",".","5"],
     [".",".",".",".","8",".",".","7","9"]
     ]
     输出: true
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i ++){
            // hori, veti, sqre分别表示行、列、小宫
            int hori = 0, veti = 0, sqre = 0;
            for(int j = 0; j < 9; j ++){
                // 由于传入为char，需要转换为int，减48
                int h = board[i][j] - 48;
                int v = board[j][i] - 48;
                int s = board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] - 48;
                // "."的ASCII码为46，故小于0代表着当前符号位"."，不用讨论
                if(h > 0){
                    hori = sodokuer(h, hori);
                }
                if(v > 0){
                    veti = sodokuer(v, veti);
                }
                if(s > 0){
                    sqre = sodokuer(s, sqre);
                }
                if(hori == -1 || veti == -1 || sqre == -1){
                    return false;
                }
            }
        }
        return true;
    }

    private int sodokuer(int n, int val){
        return ((val >> n) & 1) == 1 ? -1 : val ^ (1 << n);
    }

    /**
     * 静态类
     */
    static class Point {
        int row;
        int column;
        char s;

        private Point(int row, int column, char s) {
            this.row = row;
            this.s = s;
            this.column = column;
        }


        @Override
        public boolean equals(Object o) {
            if (getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row || column == point.column || (point.row / 3 == row / 3 && point.column / 3 == column / 3);
        }

        @Override
        public int hashCode() {
            return Objects.hash(s);
        }
    }

    public boolean isValidSudoku1(char[][] board) {
        Set<Point> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') continue;
                Point point = new Point(i, j, board[i][j]);
                if (!set.contains(point)) set.add(point);
                else return false;
            }
        }
        return true;
    }

}
