package com.zl.lqian.mapper;

import com.zl.lqian.bean.SeckillGoods;
import com.zl.lqian.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsMapper {

    @Select("select g.*, sg.stock_count as stockCount , sg.start_date as startDate, sg.end_date as endDate, sg.seckill_price as seckillPrice, sg.version from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*, sg.stock_count as stockCount, sg.start_date as startDate, sg.end_date as end_date, sg.seckill_price as seckillPrice, sg.version  from sk_goods_seckill sg left join sk_goods g  on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    //stock_count > 0 和 版本号实现乐观锁 防止超卖
    @Update("update sk_goods_seckill set stock_count = stock_count - 1, version= version + 1 where goods_id = #{goodsId} and stock_count > 0 and version = #{version}")
    public int reduceStockByVersion(SeckillGoods seckillGoods);
}
