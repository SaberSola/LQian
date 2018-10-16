package com.zl.lqian.service.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.zl.lqian.bean.OrderInfo;
import com.zl.lqian.bean.SeckillOrder;
import com.zl.lqian.bean.User;
import com.zl.lqian.service.GoodsService;
import com.zl.lqian.service.OrderService;
import com.zl.lqian.service.RedisService;
import com.zl.lqian.service.SeckillService;
import com.zl.lqian.utils.SeckillKey;
import com.zl.lqian.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SeckillServiceImpl implements SeckillService {

    private final GoodsService goodsService;

    private final OrderService orderService;

    private final RedisService redisService;

    @Autowired
    public SeckillServiceImpl(GoodsService goodsService,OrderService orderService,RedisService redisService){

        this.goodsService = goodsService;
        this.orderService = orderService;
        this.redisService = redisService;
    }

    //保证这三个操作，减库存 下订单 写入秒杀订单是一个事物
    @Transactional
    public OrderInfo seckill(Long userId, GoodsVo goods){
        //减库存
        boolean success = goodsService.reduceStock(goods);
        if (success){
            //下订单 写入秒杀订单
            return orderService.createOrder(userId, goods);
        }else {
            setGoodsOver(goods.getId());
            return null;
        }
    }

    public long getSeckillResult(long userId, long goodsId){
        SeckillOrder order = orderService.getOrderByUserIdGoodsId(userId, goodsId);
        if (order != null){
            return order.getOrderId();
        }else{
            boolean isOver = getGoodsOver(goodsId);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisService.set(SeckillKey.isGoodsOver.getPrefix(),""+ goodsId,60*1000l);
    }

    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(SeckillKey.isGoodsOver.getPrefix() + goodsId);
    }


}
