package com.zl.lqian.controller;


import com.zl.lqian.bean.SeckillMessage;
import com.zl.lqian.bean.SeckillOrder;
import com.zl.lqian.mq.RabbitSender;
import com.zl.lqian.result.CodeMsg;
import com.zl.lqian.result.Result;
import com.zl.lqian.service.GoodsService;
import com.zl.lqian.service.OrderService;
import com.zl.lqian.service.RedisService;
import com.zl.lqian.service.SeckillService;
import com.zl.lqian.utils.GoodsKey;
import com.zl.lqian.utils.HttpUtils;
import com.zl.lqian.utils.MQConstants;
import com.zl.lqian.utils.RabbitMetaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillService seckillService;

    @Autowired
    RedisService redisService;
    @Autowired
    RabbitSender rabbitSender;

    public static HashMap<Long, Boolean> localOverMap = new HashMap<Long, Boolean>();

    @PostMapping(value = "/do_seckill")
    public Result<Integer> list(@RequestParam("userId")long userId,@RequestParam("goodsId") long goodsId) {

        boolean over = localOverMap.get(goodsId);
        if (over) {
            return Result.error(CodeMsg.SECKILL_OVER);
        }
        //预减库存
        long stock = redisService.decr(GoodsKey.getGoodsStock.getPrefix() + goodsId);//10
        if (stock < 0) {
                localOverMap.put(goodsId, true);
                return Result.error(CodeMsg.SECKILL_OVER);
        }
        //判断重复秒杀
        SeckillOrder order = orderService.getOrderByUserIdGoodsId(userId, goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_SECKILL);
        }
        //这里请求入队列
        RabbitMetaMessage rabbitMetaMessage = new RabbitMetaMessage();
        /**设置交换机 */
        rabbitMetaMessage.setExchange(MQConstants.BUSINESS_EXCHANGE);
        /**指定routing key */
        rabbitMetaMessage.setRoutingKey(MQConstants.BUSINESS_KEY);
        /** 设置需要传递的消息体,可以是任意对象 */
        SeckillMessage message = new SeckillMessage();
        message.setUserId(userId);
        message.setGoodsId(goodsId);
        rabbitMetaMessage.setPayload(message);
        /** 发送消息 */
        try {
            rabbitSender.send(rabbitMetaMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.success(0);//排队中
    }


    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        String url = "http://localhost:8079/seckill/do_seckill?userId=18181818181&goodsId=1";
        for (int i = 0; i < 30 ; i++){
            executorService.execute(()->{
                HttpUtils.sendPost(url,null);
            });
        }



    }
}
