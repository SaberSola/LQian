package com.zl.demo.server;


import com.zl.demo.core.SnowFlake;
import com.zl.demo.exception.RemotingTooMuchRequestException;
import com.zl.demo.utils.GlobalConfig;
import com.zl.demo.utils.NettyUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {



    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 通过信号量来控制流量
     */
    private Semaphore semaphore = new Semaphore(GlobalConfig.HANDLE_HTTP_TPS);
    private SnowFlake snowFlake;

    public HttpServerHandler(SnowFlake snowFlake) {
        this.snowFlake = snowFlake;
    }


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        String uri = getUriNoSprit(msg);
        logger.info(">>>>>> request uri is: {}", uri);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        if (GlobalConfig.HTTP_REQUEST.equals(uri)){
            //需要信号量去申请
            if (semaphore.tryAcquire(GlobalConfig.ACQUIRE_TIMEOUTMILLIS, TimeUnit.MILLISECONDS)){
                //申请到许可
                try {
                    Long id = snowFlake.nextId();
                    logger.info("HttpServerHandler id is: {}", id);
                    response.content().writeBytes((""+id).getBytes());
                    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                }catch (Exception e){
                    logger.error("HttpServerHandler error", e);
                }finally {
                    //这里释放
                    semaphore.release();
                }
            }else { //这里表示没有申请到许可
                String info = String.format("HttpServerHandler tryAcquire semaphore timeout, %dms, waiting thread " +
                                "nums: %d availablePermit: %d",     //
                        GlobalConfig.ACQUIRE_TIMEOUTMILLIS, //
                        this.semaphore.getQueueLength(),    // 已经被申请的数量
                        this.semaphore.availablePermits()   //  剩余还可以申请的
                );
                logger.warn(info);
                throw new RemotingTooMuchRequestException(info);
            }
        }

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        logger.error("HttpServerHandler channel [{}] error and will be closed", NettyUtil.parseRemoteAddr(channel), cause);
        NettyUtil.closeChannel(channel);
    }

    private String getUriNoSprit(FullHttpRequest request) {
        String uri = request.getUri();
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        return uri;
    }
}
