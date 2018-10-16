package com.zl.lqian.service;

import com.zl.lqian.bean.OrderInfo;
import com.zl.lqian.bean.SeckillOrder;
import com.zl.lqian.bean.User;
import com.zl.lqian.vo.GoodsVo;

public interface OrderService {


    public SeckillOrder getOrderByUserIdGoodsId(long userId, long goodsId);

    public OrderInfo getOrderById(long orderId) ;

    public OrderInfo createOrder(Long userId, GoodsVo goods);
}
