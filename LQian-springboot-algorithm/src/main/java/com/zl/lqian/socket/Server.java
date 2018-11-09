package com.zl.lqian.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)throws Exception {
        new Server().service();
    }

    private int port=8888;//监听端口
    private ServerSocket serverSocket;
    public Server()throws Exception{
        serverSocket=new ServerSocket(port);
        System.out.println("服务器启动了.........");
    }
    public void service(){
        while(true){
            Socket socket=null;
            //进入阻塞，等待客户端的连接
            //如果客户端申请连接，则返回一个Socket对象，该对象和客户端的Socket对象构成一条通信渠道
            try {
                socket=serverSocket.accept();
                new SocketThread(socket).start();//有一个客户申请连接，就启动一个线程进行处理(可以考虑用线程池管理)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class SocketThread extends Thread{
        private Socket socket=null;
        public SocketThread(Socket socket){
            this.socket=socket;
        }
        public void run(){
            try {
                String user=socket.getInetAddress()+":"+socket.getPort();
                System.out.println("新客户到来:"+user);
                //读取客户端发送类的内容
                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                //向客户端发送信息
                PrintWriter writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));

                while(true){
                    String content=reader.readLine();
                    if(content==null) break;
                    System.out.println("客户端"+user+"对服务器说:"+content);
                    //服务器向客户端发送信息
                    //所以必须使用println()输出
                    //如果用print输出，则content必须加\r\n(content+"\r\n"),否则客户端的readLine()会无法读取，一直阻塞
                    //	writer.print(content+"\r\n");//原样发回
                    writer.println(content);
                    writer.flush();
                    if("bye".equalsIgnoreCase(content))
                        break;//结束通信
                }
                reader.close();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(socket!=null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }//通信结束
            }

        }
    }
}
