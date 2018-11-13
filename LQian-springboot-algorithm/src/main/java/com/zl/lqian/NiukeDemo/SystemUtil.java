package com.zl.lqian.NiukeDemo;

public class SystemUtil {

    public static boolean isAdmin(String userId){
        return userId.toLowerCase()=="admin";
    }
    public static void main(String[] args){
        System.out.println(isAdmin("Admin"));
        String s = "admin";
        System.out.println(s == "admin");


    }
}
