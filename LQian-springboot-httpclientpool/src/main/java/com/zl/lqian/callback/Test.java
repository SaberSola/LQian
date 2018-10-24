package com.zl.lqian.callback;

public class Test {

    public static void main(String[] args) {
        FooBar foo = new FooBar();
        foo.setCallBack(new ICallBack() {
            @Override
            public void postExec() {
                System.out.println("dsdfasfd");
            }
        });
        foo.doSth();//调用函数
    }
}
