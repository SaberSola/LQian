package com.zl.lqian.onehundred.String;

import java.util.Arrays;

public class PrintNumbers {


    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     *
     * @param n
     * @return
     */
    public static int[] printNumbers(int n) {
        int len = (int) Math.pow(10, 5);
        int[] res = new int[len - 1];
        for (int i = 1; i < len; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    /**
     * 大数打印
     *
     * @param n
     * @return
     */
    public  static void printNumbers2(int n) {
        //声明字符数组,用来存放一个大数
        char[] number = new char[n];
        Arrays.fill(number, '0');
        while (!incrementNumber(number)) {
            saveNumber(number); //存储数值
        }
    }

    private static boolean incrementNumber(char[] number) {
        //循环体退出标识
        boolean isBreak = false;
        //进位标识
        int carryFlag = 0;
        int l = number.length;
        for (int i = l - 1; i >= 0; i--) {
            //取第i位的数字转化位int
            int nSum = number[i] - '0' + carryFlag;
            if (i == l - 1) {
                //最低位加1
                ++nSum;
            }
            if (nSum >= 10) {
                if (i == 0) {
                    isBreak = true;
                } else {
                    //进位之后减10，并把进位标识设置为1
                    nSum -= 10;
                    carryFlag = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) (nSum + '0');
                break;
            }
        }
        return isBreak;
    }
    private static void saveNumber(char[] number) {
        boolean isBegin0 = true;
        for (char c : number) {
            if (isBegin0 && c != '0') {
                isBegin0 = false;
            }
            if (!isBegin0) {
                // 到这里并没有继续往下实现一个存储数组的版本，是因为原题其实就是要求打印数值。
                // 这道题目在leetcode上被改动成返回int数组的形式，也只是为了测试方便，
                // 本身leetcode并没有提供对应的大数测试样例，也是担心其内存溢出。
                // 总之大家知道本题的考察点所在就可以了。
                System.out.print(c);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printNumbers2(2);
    }




}
