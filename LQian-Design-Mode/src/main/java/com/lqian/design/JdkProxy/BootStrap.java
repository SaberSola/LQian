package com.lqian.design.JdkProxy;

public class BootStrap {

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        UserManager userManager = (UserManager)jdkProxy.getJDKProxy(new UserManagerImpl());
        userManager.addUser("zhang  lei", "123456");
        System.out.println("4.执行结束----------------");

    }
}
