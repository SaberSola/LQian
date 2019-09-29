package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-29
 * @Des 斐波那契数列第N项
 *   F(1)=1，F(2)=1  F(n)=F(n-1)+F(n-2)   n >= 3
 */
public class Fibonacci {


    /**
     * 递归的效率很低
     * @param n
     * @return
     */
    public static int getFibonacci(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }

    /**
     *
     * @param n
     * @return
     */
    public static Long getFibonacci2(int n){
        Long number = 1L;
        Long sum    = 1L;
        if (n <= 0){
            return 0L;
        }
        if (n == 1 || n == 2) {
            return 1L;
        }
        while (n-- > 2) {
            sum += number;
            number = sum - number;
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println(Fibonacci.getFibonacci(5));
    }





}
