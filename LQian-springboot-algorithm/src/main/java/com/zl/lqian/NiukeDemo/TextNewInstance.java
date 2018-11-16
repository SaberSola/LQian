package com.zl.lqian.NiukeDemo;

class C {
    C() {
        System.out.print("C");
    }
}

class A {
    C c = new C();

    A() {
        this("A");
        System.out.print("A");
    }

    A(String s) {
        System.out.print(s);
    }
}

  class TestNewInstance extends A {
    public  TestNewInstance() {
        super("B");
        System.out.print("B");
    }

    public static void main(String[] args) {
        TestNewInstance testNewInstance = new TestNewInstance();
    }
}
