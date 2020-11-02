package com.zl.lqian.onehundred.bit;

public class SumNums {



    public static int sumNums(int n){
        boolean b = n > 0 && ((n += sumNums(n -1 )) > 0);
        return n;

    }


    public static void main(String[] args) {
        System.out.println(sumNums(5));
    }
}
