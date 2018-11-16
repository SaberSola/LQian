package com.zl.lqian.NiukeDemo;

public class TestOverRide {

    class A {
        public A foo() {
            return this;
        }
    }

    class B extends A {
        public A foo() {
            return this;
        }
    }

    class C extends B

    {
        public A foo(B b){return b;}

    }

}