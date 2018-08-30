package com.lqian.design.ChainOfResponsibility;

public class SpecialSupport extends Support {

    private int number;                                 // 只能解决指定编号的问题

    public SpecialSupport(String name, int number) {    // 构造函数
        super(name);
        this.number = number;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNumber() == number) {
            return true;
        }
        return false;
    }
}
