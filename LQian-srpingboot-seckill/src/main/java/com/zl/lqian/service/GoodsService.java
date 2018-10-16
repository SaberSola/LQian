package com.zl.lqian.service;

import com.zl.lqian.vo.GoodsVo;

import java.util.List;

public interface GoodsService {

    public List<GoodsVo> listGoodsVo();

    public GoodsVo getGoodsVoByGoodsId(long goodsId);

    public boolean reduceStock(GoodsVo goods);
}
