package com.zl.lqian.bytedance;

/**
 * 200. 岛屿数量
 * 给岛屿的数数量
 */
public class NumIslands {


    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     *  
     * <p>
     * <p>
     * <p>
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        //深度优先遍历，找到四周为1
        if (grid == null || grid.length == 0) {
            return 0;
        }
        //行
        int nr = grid.length;
        //列
        int nc = grid[0].length;
        //岛屿的总数
        int nums = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    ++nums;
                    //以当前节点为跟节点,开始搜索
                    dfs(grid, r, c);
                }
            }
        }

        return nums;
    }

    public void dfs(char[][] grid, int r, int c) {
        //判断边界
        int nr = grid.length;
        int nc = grid[0].length;
        //搜索到边界,或者碰到为0的时候
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        //避免重复搜索,把当前的置为0
        grid[r][c] = '0';
        //前后上下搜索
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}
