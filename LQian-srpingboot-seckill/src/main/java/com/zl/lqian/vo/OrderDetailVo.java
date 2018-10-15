package com.zl.lqian.vo;

import com.zl.lqian.bean.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVo {

    private GoodsVo goods;

    private OrderInfo order;

}
