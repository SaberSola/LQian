package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-15
 * @Des ${todo}
 */
public class Solution62 {


    /**
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1）
     * ，每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * f(n) = f(n-i)*f(i)
     *
     * 最优的可以分为 f(a)*f(b)
     *             f(a)又可以继续拆
     * f(n) = f(n-i)*f(i)动态规划
     *
     * target为绳子长度
     * @param target
     * @return
     */
    public static int cutRope(int target) {
        if (target < 2){//绳子长度为1不能切分
            return 0;
        }
        if (target == 2){
            return 1;
        }
        if (target == 3){
            return 2;
        }
        int[] repos = new int[target + 1];
        int max = -1;
        repos[1] = 1;
        repos[2] = 2;
        repos[3] = 3;

        for (int i = 4; i <=target; i++){
            for (int j=1; j<= i/2; j++){
                int val = repos[j] * repos[i-j];
                max = max > val?max:val;
                repos[i] = max;
            }
        }

        max = repos[target];
        return max;
    }

    public static void main(String[] args) {
        cutRope(4);
    }
}
