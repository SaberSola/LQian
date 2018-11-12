package com.zl.lqian.NiukeDemo;

import java.util.ArrayList;
import java.util.List;

public class Test {

    static String x="1";
    static int y=1;
    public static void main(String args[]) {
        //static int z=2;  //static 不允许定义成员变量
        //System.out.println(x+y+z);

//
//        List<Integer> NumberList = new ArrayList<>();
//        NumberList.add(2);
//        NumberList.add(4);
//        NumberList.add(1);
//        NumberList.add(3);
//        NumberList.add(5);
//        for(int i =0;i<NumberList.size();++i){
//            int v = NumberList.get(i);
//            if(v%2==0){
//                NumberList.remove(v);
//            }
//        }
//        System.out.println(NumberList);

        String s="hello";
        String t="hello";
        char c[] ={'h','e','l','l','o'};
        System.out.println(s.equals(t));
        System.out.println(t.equals(c)); //equals 首先会比较类型
        System.out.println(s == t);

        System.out.println(t.equals(new String("hello")));

        Integer s1=new Integer(9);
        Integer t2=new Integer(9);
        Long u=new Long(9);

        System.out.println(s1.equals(new Integer(9)));
        Test test = new Test();

    }

    public static void  print(){
    }

    public static void print1(){}



}
