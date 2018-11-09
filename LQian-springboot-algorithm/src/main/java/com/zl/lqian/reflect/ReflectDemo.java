package com.zl.lqian.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Calendar;

/**
 * 对于基本类型的静态常量，JVM 在编译阶段会把引用此常量的代码替换成具体的常量值。
 * //注意是 String  类型的值
 * private final String FINAL_VALUE = "hello";
 *
 * if(FINAL_VALUE.equals("world")){
 *     //do something
 * }
 *
 * 编译后得到的 .class 文件（当然，编译后是没有注释的）：
 * private final String FINAL_VALUE = "hello";
 * //替换为"hello"
 * if("hello".equals("world")){
 *     //do something
 * }
 *
 */
public class ReflectDemo {

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
    public void printFields() throws Exception{

        //1.获取并输出类的名称
        Class mClass = Class.forName("com.zl.lqian.reflect.SonClass");

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
     *  mClass.getMethods(); 所有的public 的方法
     *
     *  mClass.getDeclareMethods(); 获取此类不含父类的所有方法
     */
    @Test
    public void printMethods() throws Exception{

        //1.获取并输出类的名称
        Class mClass = Class.forName("com.zl.lqian.reflect.SonClass");
        System.out.println("类的名称：" + mClass.getName());

        /**
         * 获取所有的public方法 （包括父类 ）
         */
        Method[] methods = mClass.getMethods(); //所有的public 的方法
        for (Method method : methods) {

            //获取并输出方法的访问权限（Modifiers：修饰符）
            int modifiers = method.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            //获取方法的返回类型
            Class returnType = method.getReturnType();
            System.out.print(returnType.getName() + " "
                    + method.getName() + "( ");
            //获取并输出方法的所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter:
                    parameters) {
                System.out.print(parameter.getType().getName()
                        + " " + parameter.getName() + ",");
            }
            //获取并输出方法抛出的异常
            Class[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length == 0){
                System.out.println(" )");
            }
            else {
                for (Class c : exceptionTypes) {
                    System.out.println(" ) throws "
                            + c.getName());
                }
            }
        }
    }


    /**
     * 获取私有变量方法
     * @throws Exception
     */
    @Test
    public void getPrivateMethod() throws Exception{

        TestClass testClass = new TestClass();
        Class clazz = Class.forName("com.zl.lqian.reflect.TestClass");

        //2. 获取私有方法
        //第一个参数为要获取的私有方法的名称
        //第二个为要获取方法的参数的类型，参数为 Class...，没有参数就是null
        //方法参数也可这么写 ：new Class[]{String.class , int.class}
        Method privateMethod =
                clazz.getDeclaredMethod("privateMethod", String.class, int.class);

        if (privateMethod != null) {
            //获取私有方法的访问权
            //只是获取访问权，并不是修改实际权限
            privateMethod.setAccessible(true);

            //使用 invoke 反射调用私有方法
            //privateMethod 是获取到的私有方法
            //testClass 要操作的对象
            //后面两个参数传实参
            privateMethod.invoke(testClass,"Java Reflect ", 666);
        }
    }

    /**
     * 修改私有变量
     * @throws Exception
     */
    @Test
    public void modifyPrivateFiled() throws Exception {

        TestClass testClass = new TestClass();
        Class clazz = testClass.getClass();

        //2. 获取私有变量
        Field privateField = clazz.getDeclaredField("MSG");

        if (privateField != null){ //获取私有变量

            privateField.setAccessible(true); //获取私有变量的访问权

            //修改私有变量，并输出以测试
            System.out.println("Before Modify：MSG = " + testClass.getMsg());
            privateField.set(testClass,"MODIFYIDE");
            System.out.println("After Modify：MSG = " + testClass.getMsg());

        }

    }



}
