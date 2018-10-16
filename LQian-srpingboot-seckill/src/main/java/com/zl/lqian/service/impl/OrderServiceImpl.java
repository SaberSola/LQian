package com.zl.lqian.service.impl;

import com.zl.lqian.bean.OrderInfo;
import com.zl.lqian.bean.SeckillOrder;
import com.zl.lqian.bean.User;
import com.zl.lqian.mapper.OrderMapper;
import com.zl.lqian.service.OrderService;
import com.zl.lqian.service.RedisService;
import com.zl.lqian.utils.OrderKey;
import com.zl.lqian.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderMapper orderMapper;

    private final RedisService redisService;

    @Autowired
    public OrderServiceImpl (OrderMapper orderMapper,
                             RedisService redisService){
        this.orderMapper = orderMapper;
        this.redisService = redisService;
    }

    public SeckillOrder getOrderByUserIdGoodsId(long userId, long goodsId) {
        SeckillOrder seckillOrder = null;
         Object result = redisService.get(OrderKey.getSeckillOrderByUidGid.getPrefix() + userId + "_" + goodsId,SeckillOrder.class);
         if (Objects.isNull(result)){
             return null;
         }
         if (result instanceof SeckillOrder ){
             seckillOrder = (SeckillOrder)result;
         }
         return seckillOrder;
    }

    public OrderInfo getOrderById(long orderId) {
        return orderMapper.getOrderById(orderId);
    }

    /**
     * 因为要同时分别在订单详情表和秒杀订单表都新增一条数据，所以要保证两个操作是一个事物
     */
    @Transactional
    public OrderInfo createOrder(Long userId, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getGoodsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(userId);
        orderMapper.insert(orderInfo);

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(userId);
        orderMapper.insertSeckillOrder(seckillOrder);

        redisService.set(OrderKey.getSeckillOrderByUidGid.getPrefix() + userId + "_" + goods.getId(), seckillOrder);

        return orderInfo;
    }



}
