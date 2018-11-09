package com.zl.lqian.socket;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args)throws Exception {
        new Client().talk();
    }
    private String host="localhost";
    private int port=8888;
    private Socket socket;
    public Client()throws Exception{
        socket=new Socket(host,port);
    }
    private void talk(){
        try {
            //从键盘接收输入(输入是中文的话，就乱码了，估计和OS的字符集有关)
            BufferedReader readerLocal=new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
            //从服务器接收信息
            BufferedReader readerServer=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            //向服务器发送
            PrintWriter writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));

            while(true){
                System.out.print("客户端往服务器发送信息:");
                String msg=readerLocal.readLine();//接收来自键盘的内容
                if(msg==null || "".equalsIgnoreCase(msg)) break;
                //服务器使用BufferedReader中的readLine()读取的
                //所以必须使用println()输出
                //如果用print输出，则msg必须加\r\n(msg+"\r\n"),否则服务器的readLine()会无法读取，一直阻塞
                //writer.print(msg+"\r\n");
                writer.println("bye".equalsIgnoreCase(msg)?msg:(msg+"好"));//加个"好"字，是为了测试socket传输中文是否正常，结论正常
                writer.flush();
                System.out.println("客户端接收到来自服务器的信息:"+readerServer.readLine());
                if("bye".equalsIgnoreCase(msg))
                    break;
            }
            readerLocal.close();
            readerServer.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
