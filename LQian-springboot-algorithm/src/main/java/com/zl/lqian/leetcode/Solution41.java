package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-14
 * @Des ${todo}
 */
public class Solution41 {


    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-search
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visit = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (solve(board, word, i, j, visit, 0)) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean solve(char[][] board,String word,int x,int y,boolean[][] visit,int index){
        //退出循环
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visit[x][y]){
            return false;
        }
        if (word.charAt(index) != board[x][y]){
            return false;
        }

        if (index == word.length() -1){
            return true;
        }
        visit[x][y] = true;

        boolean flag = solve(board, word, x - 1, y, visit, index + 1) ||
                solve(board, word, x + 1, y, visit, index + 1) ||
                solve(board, word, x, y - 1, visit, index + 1) ||
                solve(board, word, x, y + 1, visit, index + 1);
        visit[x][y] = false;
        return flag;
    }
}
