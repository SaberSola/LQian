package com.lqian.design.JdkProxy;

public class UserManagerImpl implements UserManager{


    @Override
    public void addUser(String userName, String password) {
        System.out.println("2.调用了新增的方法！");
        System.out.println("3.传入参数为 userName: "+userName+" password: "+password);
    }
}
