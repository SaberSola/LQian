package com.zl.lqian.bytedance;

/**
 *  50. Pow(x, n)
 *  分治的思想
 *  求X的n次方 可以为求x 的 n/2 次方 乘以 x的n/2次方
 *
 */
public class PowerX {

    public double myPow(double x, int n) {
        return n > 0 ? quickPow(x, n) : 1 / quickPow(x, n);
    }

    public double quickPow(double x, int n) {
        if (n == 1 || n == -1) {
            return x;
        }
        if (n == 0){
            return 1;
        }
        double ret = quickPow(x, n / 2);
        return n % 2 == 0 ? ret * ret : ret * ret * x;
    }
}
