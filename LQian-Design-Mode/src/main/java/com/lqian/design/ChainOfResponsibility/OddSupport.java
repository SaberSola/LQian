package com.lqian.design.ChainOfResponsibility;

public class OddSupport extends Support {


    public OddSupport(String name) {                // 构造函数
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNumber() % 2 == 1){
            return true;
        }
        return false;
    }
}
