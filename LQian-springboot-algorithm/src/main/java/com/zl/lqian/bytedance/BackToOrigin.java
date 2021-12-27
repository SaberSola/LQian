package com.zl.lqian.bytedance;

/**
 * 圆环回圆点问一
 * https://mp.weixin.qq.com/s/NZPaFsFrTybO3K3s7p7EVg
 * 0~9的环，从0出发，N步后可以走回0。
 */
public class BackToOrigin {

    /**
     * 有2种方案。分别是0->1->0和0->9->0
     * 计算走n步到0 = 走n-1步到9 + 走n-1步到1的总和
     * dp[i][j] 代表 从0出发,走i步到j的方案
     * @return
     */
    public int find(int n) {
        int length = 10;//
        int[][] dp = new int[n + 1][10];//注意是n+1，走2步，走1步。走0步
        dp[0][0] = 1;
        dp[1][1] = 1;
        dp[1][length - 1] = 1;//
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                //j-1+length是防止j-1=-1
                //%length防止j-1+length或者j+1超过数组大小，绕一圈
                dp[i][j] = dp[i - 1][(j - 1 + length) % length] + dp[i - 1][(j + 1) % length];
            }
        }
        return dp[n][0];
    }
}
