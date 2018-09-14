package com.zl.demo;

import com.zl.demo.core.SnowFlake;
import com.zl.demo.server.HttpServer;
import com.zl.demo.utils.GlobalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟启动类
 */
public class ServerStartup {

    private static final Logger logger = LoggerFactory.getLogger(ServerStartup.class);


    public static void main(String[] args){

        long datacenterId = GlobalConfig.DATACENTER_ID;
        long machineId = GlobalConfig.MACHINES_SIGN;

        if (args != null && args.length == 2) {
            datacenterId = Long.valueOf(args[0]);
            machineId = Long.valueOf(args[1]);
        }else{
            System.out.println(">>>>>You don't appoint the datacenterId and machineId argement,will use default value");
        }
        final SnowFlake snowFlake = new SnowFlake(datacenterId, machineId);

        // 启动Http服务器
        final HttpServer httpServer = new HttpServer(snowFlake);
        httpServer.init();
        httpServer.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                httpServer.shutdown();
                logger.warn(">>>>>>>>>> httpServer shutdown");
                System.exit(0);
            }
        });

    }
}
