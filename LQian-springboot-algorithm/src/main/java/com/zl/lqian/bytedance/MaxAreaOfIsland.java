package com.zl.lqian.bytedance;

public class MaxAreaOfIsland {


    /**
     * 岛屿的最大面积 695. 岛屿的最大面积
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        /**
         * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
         *
         * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
         *
         * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/max-area-of-island
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;

    }

    public int dfs(int[][] grid, int i, int j) {
        //判断边界
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int ans = 1;
        //开始以当前节点为中心,访问过的节点,徐亚置为0
        grid[i][j] = 0;
        //开始往四面八方搜索
        ans += dfs(grid, i - 1, j);
        ans +=dfs(grid, i + 1, j);
        ans += dfs(grid, i, j - 1);
        ans += dfs(grid, i, j + 1);
        return ans;
    }
}
