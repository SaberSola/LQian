package com.zl.lqian.NiukeDemo;

public class RuntimeInternDemo {


    public static void main(String[] args){



        String stra= new StringBuilder("按时吃").append("水电费费").toString();
        System.out.println(stra.intern() == stra);

        String str = new StringBuilder("计算机").append("软件").toString();

        System.out.println(str.intern() == str);




        String str2 = "SEUCalvin";//新加的一行代码，其余不变
        String str1 = new String("SEU")+ new String("Calvin");
        System.out.println(str1.intern() == str1);
        System.out.println(str1 == "SEUCalvin");





    }
}
