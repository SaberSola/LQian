package com.zl.lqian.onehundred.bit;

public class IsPowerOfTwo {



    public Boolean isPowerOfTwo(int n){
        return n > 0 && ((n & (n -1)) == 0);
    }

    public static void main(String[] args) {
        System.out.println((1 & 2) == 0);
    }
}
