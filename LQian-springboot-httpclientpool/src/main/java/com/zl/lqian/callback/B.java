package com.zl.lqian.callback;

public class B implements MyCallInterface {

    @Override
    public void method() {
        System.out.println("回调 测试 调用");
    }

    public static void main(String args[]) {
        Caller call = new Caller();
        call.setCallfuc(new B());
        call.call();
    }
}
