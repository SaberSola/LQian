package com.lqian.design.Strategy;

public  interface  Strategy {

    //获取下一句要出的手势
    public abstract Hand nextHand();

    //上一句是否获胜
    public abstract void study(boolean win);
}
