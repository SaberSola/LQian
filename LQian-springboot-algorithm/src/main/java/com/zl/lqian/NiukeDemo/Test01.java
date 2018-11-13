package com.zl.lqian.NiukeDemo;

public class Test01 {


    public static void main(String[] args)
    {
        Base base = new Son();
        base.method();
       // base.methodB(); 父类调用子类方法编译会出错
    }
}
 class Base

{
    protected Integer a = 0;
    public void method()
    {
        System.out.println("Base");
    }
}
class Son extends Base
{

    public void method()

    {


        System.out.println("Son");
    }

    public void methodB()
    {
       // float f[][] = new float[6][];

        float []f[] = new float[6][6];
        System.out.println("SonB");
    }
}