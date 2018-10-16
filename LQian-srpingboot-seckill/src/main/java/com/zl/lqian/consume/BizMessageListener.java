package com.zl.lqian.consume;

import com.alibaba.fastjson.JSONObject;
import com.zl.lqian.bean.SeckillMessage;
import com.zl.lqian.bean.SeckillOrder;
import com.zl.lqian.mq.AbstractMessageListener;
import com.zl.lqian.service.GoodsService;
import com.zl.lqian.service.OrderService;
import com.zl.lqian.service.RedisService;
import com.zl.lqian.service.SeckillService;
import com.zl.lqian.utils.LogUtil;
import com.zl.lqian.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Objects;


@Component
public class BizMessageListener extends AbstractMessageListener {

    private Logger LOGGER = LoggerFactory.getLogger(BizMessageListener.class);

    private final RedisService redisService;

    private final GoodsService goodsService;

    private final OrderService orderService;

    private final SeckillService seckillService;

    @Autowired
    public BizMessageListener(RedisService redisService, GoodsService goodsService,
                              OrderService orderService, SeckillService seckillService){

        this.goodsService  = goodsService;
        this.redisService = redisService;
        this.orderService = orderService;
        this.seckillService = seckillService;
    }

    @Override
    public void receiveMessage(Message message, MessageConverter messageConverter) {

        Object bizObj = messageConverter.fromMessage(message);
        if (Objects.isNull(bizObj)){
            LogUtil.info(LOGGER,()->"message body is null ----->");
        }
        LinkedHashMap<String,Object> messageMap = null;
        if (bizObj instanceof LinkedHashMap){
            messageMap = (LinkedHashMap)messageConverter.fromMessage(message);
        }
        LogUtil.info(LOGGER,()->"get message success:" + bizObj.toString());
        long goodsId = Long.parseLong(messageMap.get("goodsId").toString());
        long userId = Long.parseLong(messageMap.get("userId").toString());
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goodsVo.getStockCount();
        if(stock <= 0){
            return;
        }

        //判断重复秒杀
        SeckillOrder order = orderService.getOrderByUserIdGoodsId(userId, goodsId);
        if(order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        seckillService.seckill(userId, goodsVo);
        LogUtil.info(LOGGER,()->"kill success userId:{},goodsId:{}"+userId+ goodsId);
    }
}
