package com.zl.lqian.NiukeDemo;

public class Testadd {


    private static int j = 0;

    private static Boolean methodB(int k) {
        j += k;
        System.out.println(j);
        return true;
    }

    public static void methodA(int i) {
        boolean b;
        b = i < 10 | methodB(4);

        b = i < 10 || methodB(8);

    }

    public static void main(String args[]) {
        methodA(0);
        System.out.println(j);
    }
}
