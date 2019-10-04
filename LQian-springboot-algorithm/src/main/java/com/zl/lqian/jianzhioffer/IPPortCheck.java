package com.zl.lqian.jianzhioffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author zl
 * @Date 2019-09-30
 * @Des ${todo}
 */
public class IPPortCheck {

    public static void main(String[] args) {
        for (int i = 10087; i <65535; i ++){
            Socket connect = new Socket();
            try {
                connect.connect(new InetSocketAddress("103.73.161.168",i),100);//建立连接
                boolean res = connect.isConnected();//通过现有方法查看连通状态
                System.out.println(res);//true为连通
                if (res){
                    System.out.println("port:" + i);
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println("false");//当连不通时，直接抛异常，异常捕获即可
            }finally{
                try {
                    connect.close();
                } catch (IOException e) {
                    System.out.println("false");
                }
            }

        }
    }
}
