package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-29n
 * @Des 输出二进制中1的个数
 *
 */
public class NumberOfBin {

    /**
     * 把一个整数减去１再和原整数与，就会把整数最右边一个１变成０
     * @param n
     * @return
     */
    public static Integer getNumberofBin(int n){
        Integer count = 0;

        while (n != 0){
            count ++;
            n = (n - 1) & n;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(NumberOfBin.getNumberofBin(21));
    }


}
