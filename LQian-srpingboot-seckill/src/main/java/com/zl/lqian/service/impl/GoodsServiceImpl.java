package com.zl.lqian.service.impl;

import com.zl.lqian.bean.SeckillGoods;
import com.zl.lqian.mapper.GoodsMapper;
import com.zl.lqian.service.GoodsService;
import com.zl.lqian.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final int DEFAULT_MAX_RETRIES = 5;  // lock retry max times

    private final GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper){
        this.goodsMapper = goodsMapper;
    }

    /**
     * 查询商品列表
     * @return
     */
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    /**
     * 根据id查询指定商品
     * @return
     */
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    /**
     * 下单减库存
     * @return
     */
    @Transactional
    public boolean reduceStock(GoodsVo goods) {
        int numAttempts = 0;
        int ret = 0;
        SeckillGoods sg = new SeckillGoods();
        sg.setGoodsId(goods.getId());
        sg.setVersion(goods.getVersion());
        do {
            numAttempts++;
            try {
                ret = goodsMapper.reduceStockByVersion(sg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ret != 0)
                break;
        } while (numAttempts < DEFAULT_MAX_RETRIES);

        return ret > 0;
    }
}
