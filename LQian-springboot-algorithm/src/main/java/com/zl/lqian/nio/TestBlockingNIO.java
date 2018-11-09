package com.zl.lqian.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {

        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898)); //socketchannel 用来控制网络读取数据

        FileChannel inChannel = FileChannel.open(Paths.get("E://1.png"), StandardOpenOption.READ);

        //分配缓冲区

        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3. 读取本地文件，并发送到服务端
        while(inChannel.read(buf) != -1){
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        inChannel.close();
        sChannel.close();
    }


    @Test
    public void server() throws IOException{


        ServerSocketChannel ssChannel = ServerSocketChannel.open(); //用来监听SocketChannel

        FileChannel outChannel = FileChannel.open(Paths.get("E://2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        ssChannel.bind(new InetSocketAddress(9898));

        //获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        //分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //接收客户端的数据，并保存到本地
        while(sChannel.read(buf) != -1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        // 关闭通道
        sChannel.close();
        outChannel.close();
        ssChannel.close();

    }
}
