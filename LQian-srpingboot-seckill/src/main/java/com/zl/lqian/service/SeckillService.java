package com.zl.lqian.service;

import com.zl.lqian.bean.OrderInfo;
import com.zl.lqian.vo.GoodsVo;

public interface SeckillService {

    public OrderInfo seckill(Long userId, GoodsVo goods);

    public long getSeckillResult(long userId, long goodsId);
}
