package com.lqian.design.adapter;

public class Main {

    public static void main(String [] args){

        Print p = new PrintBanner("hello");
        p.printStrong();
        p.printWeak();

        AbstractPrint print = new PringBannerSe("hello");
        print.printStrong();
        print.printWeak();
    }
}
