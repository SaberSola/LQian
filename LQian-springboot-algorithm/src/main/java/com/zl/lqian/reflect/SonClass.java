package com.zl.lqian.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SonClass extends FatherClass{
    private String mSonName;
    protected int mSonAge;
    public String mSonBirthday;

    public void printSonMsg(){
        System.out.println("Son Msg - name : "
                + mSonName + "; age : " + mSonAge);
    }

    private void setSonName(String name){
        mSonName = name;
    }

    private void setSonAge(int age){
        mSonAge = age;
    }

    private int getSonAge(){
        return mSonAge;
    }

    private String getSonName(){
        return mSonName;
    }


    /**
     * 通过反射获取所有的类变量
     *
     * 调用 getFields() 方法，输出 SonClass 类以及其所继承的父类( 包括 FatherClass 和 Object ) 的 public 方法。
     *
     * 调用 getDeclaredFields() ， 输出 SonClass 类的所有成员变量，不问访问权限。
     *
     * note getDeclaredFields() ,不包括父类
     */
    @Test
    public void printFields(){

        //1.获取并输出类的名称
        Class mClass = SonClass.class;

        System.out.println("类的名称：" + mClass.getName());
        //获取所有的public的变量 包括父类
        Field[] fields = mClass.getFields();
        fields = mClass.getDeclaredFields();
        //遍历并输出
        for (Field field : fields){
            //获取访问权限并输出
            int modifiers = field.getModifiers();
            System.out.println(Modifier.toString(modifiers) + " " + "获取的访问权限");
            //输出变量的类型及变量名
            System.out.println(field.getType().getName()
                    + "   " + field.getName());
        }

    }


    /**
     * 获取此类的所有的方法
     *
     */
    @Test
    public void printMethods(){

        //1.获取并输出类的名称
        Class mClass = SonClass.class;
        System.out.println("类的名称：" + mClass.getName());

        /**
         * 获取所有的public方法 （包括父类 ）
         */
         Method[] methods = mClass.getMethods(); //所有的public 的方法
        for (Method method : methods) {

            //获取并输出方法的访问权限（Modifiers：修饰符）
        }


    }


}
