package com.lqian.design.adapter;

public class PringBannerSe extends AbstractPrint {


    private Banner banner;

    public PringBannerSe(String string){
        this.banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithAster();
    }

    @Override
    public void printStrong() {
        banner.showWithParen();
    }
}
