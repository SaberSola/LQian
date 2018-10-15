package com.zl.lqian.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SeckillGoods {

    private Long id;

    private Long goodsId;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;

    private int version;

}
