package com.zl.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author zl
 * @Date 2018-12-21
 * @Des ${todo}
 */
public class CodecUtil {


    public static ByteBuffer read(SocketChannel channel){

        //注意不考虑拆包的情况
        ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);
        try {
            int count = channel.read(byteBuffer);  //从channel 读到 buffer
            if(count == -1){
                return null;
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return byteBuffer;
    }

    public static void write(SocketChannel channel, String content) {
        ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);
        try {
            byteBuffer.put(content.getBytes("UTF-8")); //从buffer 写入到Channel 中
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //写入到channel
        byteBuffer.flip();
        try {
            // 注意，不考虑写入超过 Channel 缓存区上限。
            channel.write(byteBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String newString(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        System.arraycopy(buffer.array(), buffer.position(), bytes, 0, buffer.remaining());
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
