package com.zl.lqian.leetcode;

import java.util.HashSet;

/**
 * @Author zl
 * @Date 2020-05-23
 * @Des ${todo}
 */
public class Solution56 {


    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * <p>
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * <p>
     * 示例:
     * <p>
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     * <p>
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     *
     * @param board
     */
    public void solve(char[][] board) {
        //行
        int rows = board.length;
        if (rows == 0) {
            return;
        }
        //
        HashSet<String> memoization = new HashSet<>();
        //列
        int cols = board[0].length;
        //上下访问
        boolean[][] visited = new boolean[rows][cols];
        //从最上一行开始搜索
        for (int i = 0; i < cols; i++) {
            //最上边一行的所有 O 做 DFS
            if (board[0][i] == 'O') {
                dfs(0, i, board, visited);
            }
            //最下边一行的所有 O 做 DFS
            if (board[board.length - 1][i] == 'O') {
                dfs(board.length - 1, i, board, visited);
            }
        }
        //从左右两边开始搜索
        for (int i = 1; i < rows - 1; i++) {
            //最左边一列的所有 O 做 DFS
            if (board[i][0] == 'O') {
                dfs(i, 0, board, visited);
            }
            //最右边一列的所有 O 做 DFS
            if (board[i][board[0].length - 1] == 'O') {
                dfs(i, board[0].length - 1, board, visited);
            }
        }
        //把所有没有标记过的O改为x
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //省略了对 X 的判断，把 X 变成 X 不影响结果
                if (!visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }

    }


    private void dfs(int i, int j, char[][] board, boolean[][] visited) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        if (board[i][j] == 'O') {
            visited[i][j] = true;
            dfs(i + 1, j, board, visited);
            dfs(i, j + 1, board, visited);
            dfs(i, j - 1, board, visited);
            dfs(i - 1, j, board, visited);
        }


    }
}
