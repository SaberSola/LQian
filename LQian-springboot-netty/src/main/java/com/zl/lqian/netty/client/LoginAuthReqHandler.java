package com.zl.lqian.netty.client;

import com.zl.lqian.netty.codec.NettyMessageDecoder;
import com.zl.lqian.netty.codec.NettyMessageEncoder;
import com.zl.lqian.netty.struct.Header;
import com.zl.lqian.netty.struct.MessageType;
import com.zl.lqian.netty.struct.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class LoginAuthReqHandler extends ChannelHandlerAdapter {


    private static final Log LOG = LogFactory.getLog(LoginAuthReqHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这里表示握手成功 登陆成功
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_RESP
                .value()) {

            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                // 握手失败，关闭连接
                ctx.close();
            } else {
                LOG.info("Login is ok : " + message);
                //透传
                ctx.fireChannelRead(msg);
            }
        }else {
            //TODO 不是这种类型的消息也透传
            ctx.fireChannelRead(msg);
        }
    }


    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    public void encode(NettyMessage nettyMessage,ByteBuf byteBuf) throws Exception{
        NettyMessageEncoder encoder = new NettyMessageEncoder();
        encoder.encode(null,nettyMessage,byteBuf);
    }

    public NettyMessage getMessage() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setLength(123);
        header.setSessionID(99999);
        header.setType((byte) 1);
        header.setPriority((byte) 7);
        Map<String, Object> attachment = new HashMap<String, Object>();
        for (int i = 0; i < 10; i++) {
            attachment.put("ciyt --> " + i, "lilinfeng " + i);
        }
        header.setAttachment(attachment);
        nettyMessage.setHeader(header);
        nettyMessage.setBody("abcdefg-----------------------AAAAAA");
        return nettyMessage;
    }
}
