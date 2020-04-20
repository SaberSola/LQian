package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-18
 * @Des ${todo}
 */
public class Solution63 {

    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 \begin{bmatrix} a & b & c &e \\ s & f & c & s \\ a & d & e& e\\ \end{bmatrix}\quad
     * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        //转换为二维数组
        char[][] board = new char[rows][cols];
        int index = 0;
        int x = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (index >= cols) {
                index = 0;
                x++;
            }
            board[x][index] = matrix[i];
            index++;
        }
        StringBuilder word = new StringBuilder();
        for (int j = 0; j < str.length; j++) {
            word.append(str[j]);
        }
        boolean[][] vis = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (solve(board, word.toString(), i, j, vis, 0)) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean solve(char[][] board, String word, int x, int y, boolean[][] vis, int index) {
        //越界
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || vis[x][y]) {
            return false;
        }
        //字符串不相等
        if (word.charAt(index) != board[x][y]){
            return false;
        }
        //找到了
        if (index == word.length() - 1){
            return true;
        }
        vis[x][y] = true;
        //开始写递归就是上下左右开始查找
        boolean flag = solve(board, word, x - 1, y, vis, index + 1) ||
                solve(board, word, x + 1, y, vis, index + 1) ||
                solve(board, word, x, y - 1, vis, index + 1) ||
                solve(board, word, x, y + 1, vis, index + 1);
        vis[x][y] = false;
        return flag;

    }
}
