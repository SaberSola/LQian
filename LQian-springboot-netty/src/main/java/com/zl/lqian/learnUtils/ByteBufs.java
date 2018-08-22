package com.zl.lqian.learnUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufs {


    public static void main(String[] args) throws Exception{

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(1);

    }
}
