package com.zl.demo.core;

import com.zl.demo.concurrent.DistributorThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseServer implements Server  {

    protected Logger logger = LoggerFactory.getLogger(BaseServer.class);

    protected static final int MAX_THREAD = Runtime.getRuntime().availableProcessors() << 1;

    protected DefaultEventLoopGroup defLoopGroup;

    protected NioEventLoopGroup bossGroup;//接收客户端的线程池

    protected NioEventLoopGroup workGroup; //真正处理业务的线程池

    protected NioServerSocketChannel ssch;

    protected ChannelFuture cf;

    protected ServerBootstrap b;

    protected int port;


    public void init(){
        defLoopGroup = new DefaultEventLoopGroup(MAX_THREAD,
                DistributorThreadFactory.create("DEFAULTEVENTLOOPGROUP",false));
        bossGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(),
                DistributorThreadFactory.create("BOSS",false));
        workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 10,
                DistributorThreadFactory.create("work",false));

        b = new ServerBootstrap();
    }
    @Override
    public void shutdown() {
        if (defLoopGroup != null) {
            defLoopGroup.shutdownGracefully();
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        logger.info("Server EventLoopGroup shutdown finish");
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
