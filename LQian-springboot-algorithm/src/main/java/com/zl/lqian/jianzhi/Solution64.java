package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-19
 * @Des ${todo}
 */
public class Solution64 {

    public int count = 0;

    /**
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {

        //构建二位数组
        int [][] roots = new int[rows][cols];
        solve(0,0,threshold,rows,cols,roots);
        return count;
    }

    private int cul(int x,int y){
        int sum = 0;
        while (x != 0){
            sum +=x%10;
            x=x/10;
        }
        while (y != 0){
            sum +=y%10;
            y=y/10;
        }
        return sum;
    }
    private void solve(int x,int y,int threshold,int rows,int cols,int[][] roots){
        //退出条件
        if (x < 0 || x>= rows || y<0 || y>= cols || roots[x][y] == 1 || cul(x, y) > threshold){
            return;
        }
        roots[x][y] = 1;
        //递归
        count++;
        //不用回溯
        solve(x+1,y,threshold,rows,cols,roots);
        solve(x,y+1,threshold,rows,cols,roots);
    }

}
