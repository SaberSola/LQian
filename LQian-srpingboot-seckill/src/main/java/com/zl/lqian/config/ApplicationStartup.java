package com.zl.lqian.config;

import com.zl.lqian.controller.SeckillController;
import com.zl.lqian.service.GoodsService;
import com.zl.lqian.service.RedisService;
import com.zl.lqian.utils.GoodsKey;
import com.zl.lqian.utils.LogUtil;
import com.zl.lqian.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *      ContextStartedEvent ： 上下文启动事件
 *      ContextRefreshedEvent：上下文刷新事件
 *      ContextClosedEvent：上下文关闭事件
 *      ContextStoppedEvent：上下文停止事件
 * 1、 ContextRefreshedEvent：ApplicationContext容器初始化或者刷新时触发该事件。
 * 2、 ContextStartedEvent：当使用ConfigurableApplicationContext接口的start()方法启动ApplicationContext容器时触发该事件。
 * 3、 ContextClosedEvent：当使用ConfigurableApplicationContext接口的close()方法关闭ApplicationContext容器时触发该事件。
 * 4、 ContextStopedEvent: 当使用ConfigurableApplicationContext接口的stop()方法停止ApplicationContext容器时触发该事件。
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {


    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class);

    private final GoodsService goodsService;

    private final RedisService redisService;

    @Autowired
    public ApplicationStartup(GoodsService goodsService,RedisService redisService){
        this.goodsService = goodsService;
        this.redisService = redisService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        LogUtil.info(LOGGER,()->"System initing ........start");
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
        if (goodsVoList == null) {
            return;
        }
        //todo 这里最好判断缓存中是否存在，再本工程中不重要 不在判断
        for (GoodsVo goods : goodsVoList) {
            redisService.set(GoodsKey.getGoodsStock.getPrefix() + goods.getId(), goods.getStockCount());
            SeckillController.localOverMap.put(goods.getId(),false);
        }
        LogUtil.info(LOGGER,()->"System initing ........end");

    }
}
