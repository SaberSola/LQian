package com.lqian.design.Facade;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Database {

    private Database(){}

    public static Properties getProperties(String dbname) {

        String filename = dbname + ".txt";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filename));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Warning: " + filename + " is not found.");
        }
        return prop;


    } // 根据数据库名获取Properties
}
