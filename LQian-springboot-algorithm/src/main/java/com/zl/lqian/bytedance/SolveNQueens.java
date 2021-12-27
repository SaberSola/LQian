package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 */
public class SolveNQueens {

    List<List<String>> res = new ArrayList<>();

    /* 输入棋盘的边长n，返回所有合法的放置 */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] c : board){
            Arrays.fill(c, '.');
        }
        backtrack(board, 0);
        return res;
    }

    public void backtrack(char[][] board, int row) {
        //如果每一行都已经放置了,记录结果
        if (row == board.length){
            res.add(charToList(board));
            return;
        }
        //当前行的每一列都可能放置皇后
        int n = board[row].length;
        for (int col = 0; col < n; col++){
            //排除可以攻击的格子
            if (!isValid(board,row,col)){
                continue;
            }
            board[row][col] = 'Q';
            backtrack(board,row+1);
            board[row][col] = '.';
        }

    }

    /**
     * 判断是否存在相互攻击的皇后
     * 三个方向,同一列,右上方,左上方
     * @param board
     * @param row
     * @param col
     * @return
     */
    public boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        //检查列
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //右上方
        for (int i = row - 1, j = col + 1; i >=0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        //左下方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;


    }


    public List charToList(char[][] board) {
        List<String> list = new ArrayList<>();

        for (char[] c : board) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
}
