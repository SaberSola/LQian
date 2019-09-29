package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-29
 * @Des 求base的exponent次方，不得使用库函数，不需要考虑大数问题
 */
public class Power {


    /**
     * 对输入的数进行条件判断后计算结果
     *
     * @param base
     * @param exponent
     * @return
     * @throws Exception
     */
    public static double power(double base, int exponent) throws Exception {
        double result = 0.0;

        if (equal(base, 0.0) && exponent < 0) {
            throw new Exception("０的负数次幂无意义");
        }

        if (equal(exponent, 0)) {
            return 1.0;
        }

        if (exponent < 0) {
            result = powerWithExponent(1.0 / base, -exponent);
        } else {
            result = powerWithExponent(base, exponent);
        }

        return result;
    }

    /**
     * 幂计算
     *
     * @param base
     * @param exponent
     * @return
     */
    private static double powerWithExponent(double base, int exponent) {
        double result = 1.0;

        for (int i = 1; i <= exponent; i++) {
            result = result * base;
        }

        return result;
    }

    /**
     * 判断两个double型数值是否相等（有误差）
     *
     * @param num1
     * @param num2
     * @return
     */
    private static boolean equal(double num1, double num2) {
        return (num1 - num2 > -0.0000001) && num1 - num2 < 0.0000001;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(Power.power(1.2,2));
    }
}
