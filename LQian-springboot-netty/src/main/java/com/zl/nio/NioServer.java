package com.zl.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author zl
 * @Date 2018-12-21
 * @Des ${todo}
 */
public class NioServer {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector; //选择器


    public NioServer() throws IOException {

        //打开Server socket channel
        serverSocketChannel = ServerSocketChannel.open();
        //配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定Serverport
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
        //创建Selector
        selector = Selector.open();
        //注册Channel 到 Selector 上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //接入事件
        System.out.println("Server 启动完成");

        handleKeys();
    }

    private void handleKeys() throws IOException {

        while (true) {
            //通过selector 选择 Channel
            int selectNums = selector.select(30 * 1000l);
            if (selectNums == 0) {
                continue;
            }
            System.out.println("选择 Channel 数量：" + selectNums);
            //遍历可选择的Channel 的 SelectionKey的集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //移除要处理
                iterator.remove();
                if (!key.isValid()) { // 忽略无效的 SelectionKey
                    continue;
                }
                handleKey(key);
            }
        }
    }
    private void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()){ //就绪事件
            handleAcceptableKey(key);
        }
        // 读就绪
        if (key.isReadable()) {
            handleReadableKey(key);
        }
        // 写就绪
        if (key.isWritable()) {
            handleWritableKey(key);
        }
    }

    /**
     *
     * 处理客户端介入事件
     * @param key
     * @throws IOException
     */
    private void handleAcceptableKey(SelectionKey key) throws IOException{

        //接受客户端的channel
        SocketChannel clientSocketChannel = ((ServerSocketChannel) key.channel()).accept();
        //配置为非阻塞
        clientSocketChannel.configureBlocking(false);
        System.out.println("接受新的 Channel");
        //注册到 Selector
        clientSocketChannel.register(selector,SelectionKey.OP_READ,new ArrayList<>());

    }

    /**
     *
     * 处理客户端读事件
     * @param key
     * @throws IOException
     */
    private void handleReadableKey(SelectionKey key) throws IOException{

        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        //读取数据
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);

        if (readBuffer == null){
            System.out.println("断开 Channel");
            clientSocketChannel.register(selector, 0); //一直block
            return;
        }
        //开始打印数据
        if (readBuffer.position() > 0){

            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取数据：" + content);
            //添加到响应队列
            List<String> responseQueue = (ArrayList<String>) key.attachment();
            responseQueue.add(content);
            clientSocketChannel.register(selector, SelectionKey.OP_WRITE, key.attachment());
        }
    }
    private void handleWritableKey(SelectionKey key) throws IOException{
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        List<String> responseQueue = (ArrayList<String>) key.attachment();
        for (String content : responseQueue) {
            // 打印数据
            System.out.println("写入数据：" + content);
            // 返回
            CodecUtil.write(clientSocketChannel, content);  //重新写入到channel
        }
        responseQueue.clear();
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);

    }
    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
    }

}
